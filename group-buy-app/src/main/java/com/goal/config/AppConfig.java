package com.goal.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class AppConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {

        return new ThreadPoolExecutor(3, 6, 10,
                TimeUnit.MINUTES, new LinkedBlockingQueue<>());

    }

    @Bean
    RedissonClient redissonClient(org.springframework.boot.autoconfigure.data.redis.RedisProperties redisProperties) {
        Config config = new Config();

        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress(String.format("redis://%s:%s", redisProperties.getHost(), redisProperties.getPort()));
        singleServerConfig.setDatabase(redisProperties.getDatabase());
        singleServerConfig.setPassword(redisProperties.getPassword());

        return Redisson.create(config);
    }

}
