package com.goal.config;

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


}
