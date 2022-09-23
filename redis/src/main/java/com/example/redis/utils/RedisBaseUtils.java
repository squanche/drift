/*
 * @(#) RedisUtils.java 2018/09/18
 *
 * Copyright 2018 CEC(Fujian) Healthcare Big Data Operation Service Co., Ltd. All rights reserved.
 */
package com.example.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static ch.qos.logback.core.CoreConstants.EMPTY_STRING;

/**
 * <code>RedisUtils</code>
 * <p>
 * redicache 工具类
 *
 * @author dengrijin<dengrijin @ cecdat.com>
 * @version v0.1 2018/09/18
 * @see
 * @since JDK1.8
 */
@Component
public class RedisBaseUtils {
    @Autowired
    protected RedisTemplate redisTemplate;


    /**
     * 批量删除对应的value
     *
     * @param keys
     * @return
     * @throws
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     * @return
     * @throws
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     * @return
     * @throws
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result;
        result = redisTemplate.opsForValue().get(key);
        if (result == null) {
            return null;
        }
        return result;
    }

    /**
     * 写入缓存，不过期
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存，设置过期时间
     *
     * @param key
     * @param value
     * @param expireTime 时间间隔（单位：秒）
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @param expireDate 过期时间戳
     * @return
     */
    public boolean set(final String key, Object value, Date expireDate) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expireAt(key, expireDate);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入数据，value为map
     *
     * @param key
     * @param value 集合对象
     * @return
     * @throws
     */
    public boolean hmset(String key, Map<String, String> value) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().putAll(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取hashMap
     *
     * @param
     * @return
     * @throws
     */
    public Map<String, String> hmget(String key) {
        Map<String, String> result = null;
        try {
            result = redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从redis中取出值
     *
     * @param key
     * @return
     */


    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */

    /**
     * 写入缓存，设置过期时间
     *
     * @param key
     * @param value
     * @param expireTime 时间间隔（单位：秒）
     * @return
     */

    /**
     * 设置过期时间
     *
     * @param key
     * @param timeout
     * @param timeUnit 过期时间戳
     * @return
     * @throws
     */
    public boolean setExpire(String key, long timeout, TimeUnit timeUnit) {
        boolean result = false;
        if (key == null || EMPTY_STRING.equals(key)) {
            return false;
        }
        try {
            redisTemplate.expire(key, timeout, timeUnit);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param expireTime 过期时间间隔
     * @return
     * @throws
     */
    public void setExpire(String key, Long expireTime) {
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 获取过期时间
     *
     * @param key 键
     * @return
     * @throws
     */
    public Long getExpire(String key) {
        Long result = 0L;
        if (key == null || EMPTY_STRING.equals(key)) {
            return result;
        }
        try {
            result = redisTemplate.getExpire(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从左边添加对象
     *
     * @param key   列表名称
     * @param value 对象
     * @return
     * @throws
     */
    public boolean leftPush(String key, Object value) {
        boolean result = false;
        if (key == null || value == null || EMPTY_STRING.equals(key)) {
            return result;
        }
        try {
            redisTemplate.opsForList().leftPush(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从右边添加对象
     *
     * @param key   列表名称
     * @param value 对象
     * @return
     * @throws
     */
    public boolean rightPush(String key, Object value) {
        boolean result = false;
        if (key == null || value == null || EMPTY_STRING.equals(key)) {
            return result;
        }
        try {
            redisTemplate.opsForList().rightPush(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 从左边抛出
     *
     * @param
     * @return
     * @throws
     */
    public Object listLeftPop(String key) {
        Object result = null;
        if (key == null || EMPTY_STRING.equals(key)) {
            return null;
        }
        try {
            result = redisTemplate.opsForList().leftPop(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从右边抛出
     *
     * @param
     * @return
     * @throws
     */
    public Object listRightPop(String key) {
        Object result = null;
        if (key == null || EMPTY_STRING.equals(key)) {
            return null;
        }
        try {
            result = redisTemplate.opsForList().rightPop(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}