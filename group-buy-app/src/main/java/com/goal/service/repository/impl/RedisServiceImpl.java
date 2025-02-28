package com.goal.service.repository.impl;

import com.goal.service.repository.IRedisService;
import jakarta.annotation.Resource;
import org.redisson.api.*;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RedisServiceImpl implements IRedisService {

    @Resource
    private RedissonClient redissonClient;

    @Override
    public <T> void set(String key, T value) {
        redissonClient.getBucket(key).setAndKeepTTL(value);
    }

    @Override
    public <T> void setWithAliveTime(String key, T value, Duration duration) {
        redissonClient.getBucket(key).set(value, duration);
    }

    @Override
    public <T> void setWithAliveTime(String key, T value, Instant instant) {
        redissonClient.getBucket(key).set(value, Duration.ofMillis(instant.toEpochMilli() - System.currentTimeMillis()));
    }

    @Override
    public void expireAt(String key, Instant instant) {
        if (!redissonClient.getBucket(key).isExists()) {
            return;
        }
        redissonClient.getBucket(key).expire(instant);
    }

    @Override
    public void extendALiveTime(String key, Duration duration) {
        if (!redissonClient.getBucket(key).isExists()) {
            return;
        }

        long expireTime = redissonClient.getBucket(key).getExpireTime();
        redissonClient.getBucket(key).expire(Instant.ofEpochMilli(expireTime + duration.toMillis()));
    }

    @Override
    public <T> T get(String key) {
        return redissonClient.<T>getBucket(key).get();
    }

    @Override
    public <T> T get(String key, Class<T> type) {
        return redissonClient.<T>getBucket(key).get();
    }

    @Override
    public <T> RList<T> getList(String key) {
        return redissonClient.getList(key);
    }

    @Override
    public boolean remove(String key) {
        return redissonClient.getBucket(key).delete();
    }

    @Override
    public boolean isExist(String key) {
        return redissonClient.getBucket(key).isExists();
    }

    @Override
    public boolean isNotExist(String key) {
        return !isExist(key);
    }

    @Override
    public <T> RSet<T> getSet(String key) {
        return redissonClient.getSet(key);
    }

    @Override
    public <K, V> RMap<K, V> getMap(String key) {
        return redissonClient.getMap(key);
    }

    @Override
    public RAtomicLong getAtomicLong(String key) {
        return redissonClient.getAtomicLong(key);
    }

    @Override
    public RLock getLock(String key) {
        return redissonClient.getLock(key);
    }

    @Override
    public List<String> keys(String pattern) {
        RKeys keys = redissonClient.getKeys();
        return keys.getKeysStreamByPattern(pattern).collect(Collectors.toList());
    }

    @Override
    public RBitSet getBitSet(String tagId) {
        return redissonClient.getBitSet(tagId);
    }

}
