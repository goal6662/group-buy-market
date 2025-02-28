package com.goal.service.repository;

import jakarta.annotation.Resource;
import org.redisson.api.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RedisRepository {

    @Resource
    private RedissonClient redissonClient;

    
    public <T> void set(String key, T value) {
        redissonClient.getBucket(key).setAndKeepTTL(value);
    }

    
    public <T> void setWithAliveTime(String key, T value, Duration duration) {
        redissonClient.getBucket(key).set(value, duration);
    }

    
    public <T> void setWithAliveTime(String key, T value, Instant instant) {
        redissonClient.getBucket(key).set(value, Duration.ofMillis(instant.toEpochMilli() - System.currentTimeMillis()));
    }

    
    public void expireAt(String key, Instant instant) {
        if (!redissonClient.getBucket(key).isExists()) {
            return;
        }
        redissonClient.getBucket(key).expire(instant);
    }

    
    public void extendALiveTime(String key, Duration duration) {
        if (!redissonClient.getBucket(key).isExists()) {
            return;
        }

        long expireTime = redissonClient.getBucket(key).getExpireTime();
        redissonClient.getBucket(key).expire(Instant.ofEpochMilli(expireTime + duration.toMillis()));
    }

    
    public <T> T get(String key) {
        return redissonClient.<T>getBucket(key).get();
    }

    
    public <T> T get(String key, Class<T> type) {
        return redissonClient.<T>getBucket(key).get();
    }

    
    public <T> RList<T> getList(String key) {
        return redissonClient.getList(key);
    }

    
    public boolean remove(String key) {
        return redissonClient.getBucket(key).delete();
    }

    
    public boolean isExist(String key) {
        return redissonClient.getBucket(key).isExists();
    }

    
    public boolean isNotExist(String key) {
        return !isExist(key);
    }

    
    public <T> RSet<T> getSet(String key) {
        return redissonClient.getSet(key);
    }

    
    public <K, V> RMap<K, V> getMap(String key) {
        return redissonClient.getMap(key);
    }

    
    public RAtomicLong getAtomicLong(String key) {
        return redissonClient.getAtomicLong(key);
    }

    
    public RLock getLock(String key) {
        return redissonClient.getLock(key);
    }

    
    public List<String> keys(String pattern) {
        RKeys keys = redissonClient.getKeys();
        return keys.getKeysStreamByPattern(pattern).collect(Collectors.toList());
    }
    
}
