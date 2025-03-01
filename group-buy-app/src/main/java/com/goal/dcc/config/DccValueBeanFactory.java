package com.goal.dcc.config;

import com.goal.common.Constants;
import com.goal.dcc.annotation.DccValue;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Configuration
public class DccValueBeanFactory implements BeanPostProcessor {

    @Value("${app.config.dcc.url}")
    private String TOPIC_URL;

    @Resource
    private RedissonClient redissonClient;

    private final Map<String, Object> dccObjGroup = new ConcurrentHashMap<>();

    @Bean("dccTopic")
    public RTopic redisTopicListener(RedissonClient redissonClient) {

        RTopic topic = redissonClient.getTopic(TOPIC_URL);
        topic.addListener(String.class, (charSequence, s) -> {
            String[] split = s.split(Constants.SPLITTER);

            // 获取值
            String attribute = split[0];
            String key = TOPIC_URL + attribute;
            String value = split[1];

            // 设置值
            RBucket<String> bucket = redissonClient.getBucket(key);
            if (!bucket.isExists()) {
                return;
            }
            bucket.set(value);

            Object objBean = dccObjGroup.get(key);
            if (objBean == null) {
                return;
            }

            Class<?> objBeanClass = objBean.getClass();
            if (AopUtils.isAopProxy(objBean)) {
                // 获取目标对象
                objBeanClass = AopUtils.getTargetClass(objBean);
            }

            // 设置对象的值
            try {
                Field field = objBeanClass.getDeclaredField(attribute);
                field.setAccessible(true);
                field.set(objBean, value);
                field.setAccessible(false);

                log.info("DCC 节点监听，动态设置值 {} {}", key, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        return topic;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Class<?> targetBeanClass = bean.getClass();
        Object targetBeanObject = bean;
        if (AopUtils.isAopProxy(bean)) {
            targetBeanClass = AopUtils.getTargetClass(bean);
            targetBeanObject = AopProxyUtils.getSingletonTarget(bean);
        }

        // 获取所有字段（含私有字段）
        Field[] fields = targetBeanClass.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(DccValue.class)) {
                continue;
            }

            DccValue dccValue = field.getAnnotation(DccValue.class);
            String value = dccValue.value();
            if (StringUtils.isBlank(value)) {
                throw new RuntimeException("...");
            }

            // 获取key和val
            String[] split = value.split(Constants.COMMENT);
            String key = TOPIC_URL.concat(split[0]);

            try {
                String defaultValue = split.length == 2 ? split[1] : null;
                if (StringUtils.isBlank(defaultValue)) {
                    throw new RuntimeException("key does not have value...");
                }

                String setValue = defaultValue;
                RBucket<String> bucket = redissonClient.getBucket(key);
                if (!bucket.isExists()) {
                    bucket.set(defaultValue);
                } else {
                    setValue = bucket.get();
                }

                field.setAccessible(true);
                field.set(targetBeanObject, setValue);
                field.setAccessible(false);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            dccObjGroup.put(key, targetBeanObject);
        }

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
