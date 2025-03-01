package com.goal.service.repository;

import org.redisson.api.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public interface IRedisService {

    /**
     * 设置值：不更新过期时间
     * @param key key
     * @param value value 无特殊需求，无需转为JSON字符串，可直接存储对象
     * @param <T> 值类型
     */
    <T> void set(String key, T value);

    /**
     * 设置缓存同时指定过期时间
     * @param key key
     * @param value value 无特殊需求，无需转为JSON字符串，可直接存储对象
     * @param duration 从设置开始的持续时间
     */
    <T> void setWithAliveTime(String key, T value, Duration duration);

    /**
     * 设置缓存同时指定过期时间
     * @param key key
     * @param value value 无特殊需求，无需转为JSON字符串，可直接存储对象
     * @param instant 过期的时刻
     */
    <T> void setWithAliveTime(String key, T value, Instant instant);

    /**
     * 设置过期时间
     * @param key key
     * @param instant 过期时间
     */
    void expireAt(String key, Instant instant);

    /**
     * 延长键的过期时间
     *
     * @param key      键
     * @param duration 延长的时间
     */
    void extendALiveTime(String key, Duration duration);

    /**
     * 获取值
     * @param key key
     * @return 缓存值
     * @param <T> 值类型
     */
    <T> T get(String key);

    /**
     * 获取值
     * @param key key
     * @param type 类型
     * @return 缓存值
     * @param <T> 值类型
     */
    <T> T get(String key, Class<T> type);


    <T> RList<T> getList(String key);

    /**
     * 移除缓存
     * @param key key
     * @return 删除结果
     */
    boolean remove(String key);

    /**
     * 判断缓存是否存在
     * @param key key
     * @return true-存在、false-不存在
     */
    boolean isExist(String key);

    boolean isNotExist(String key);

    <T> RSet<T> getSet(String key);

    <K, V> RMap<K, V> getMap(String key);

    RAtomicLong getAtomicLong(String key);

    /**
     * 获取所
     * @param key key
     * @return 分布式锁
     */
    RLock getLock(String key);

    List<String> keys(String pattern);

    RBitSet getBitSet(java.lang.String tagId);
}
