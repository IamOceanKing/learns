/*
 * Copyright (C) 2016-2020 jindan.com Corporation
 *
 */
package com.oceanking.learn.spring.springboot.config.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 工具类处理 : Redis 处理
 */
@Component
public class RedisHelper {

    public static final Logger logger = LogManager.getLogger(RedisHelper.class);

    /**
     * :
     */
    private final static String COLON = ":";

    /**
     * 文字列模版
     */

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 存在性判断
     *
     * @param key key
     * @return true: 存在 false: 不存在
     */
    public boolean exists(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 获取 key 值 (String, String)
     *
     * @param key key
     * @return key 值
     */
    public String get(String key) {
        try {
            return (String) redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "";
        }
    }


    /**
     * 设置 key 值 (String, String)
     *
     * @param key   key
     * @param value String
     */
    public void set(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 设置 key 值 (String, String)
     *
     * @param key     key
     * @param value   String
     * @param timeout 时效时间
     */
    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        try {
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expire(key, timeout, timeUnit);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 设置 key 值 (String, String)
     *
     * @param key   key
     * @param value String
     */
    public void set(String key, String value, Date date) {
        try {
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expireAt(key, date);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获取 key 值 (String, Object)
     *
     * @param key   键
     * @param clazz 对象
     * @return key 值
     */
    public <T> T get(String key, Class<T> clazz) {
        try {
            String str = (String) redisTemplate.opsForValue().get(key);
            return JSON.parseObject(str, clazz);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取 key 值 (String, type)
     *
     * @param key  键
     * @param type 对象
     * @return key 值
     */
    public <T> T get(String key, TypeReference<T> type) {
        try {
            String str = (String) redisTemplate.opsForValue().get(key);
            return JSON.parseObject(str, type);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 设置 key 值 (String, Object)
     *
     * @param key 键
     * @param obj 对象
     */
    public void set(String key, Object obj) {
        try {
            redisTemplate.opsForValue().set(key, JSON.toJSONString(obj));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 设置 key 值 (String, Object)
     *
     * @param key      键
     * @param obj      对象
     * @param timeout  失效时间
     * @param timeUnit 单位
     */
    public void set(String key, Object obj, long timeout, TimeUnit timeUnit) {
        set(key, JSON.toJSONString(obj), timeout, timeUnit);
    }

    /**
     * 设置自增值 (String, Object)
     *
     * @param key 键
     */
    public Long incr(String key) {
        try {
            return redisTemplate.opsForValue().increment(key, 1L);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 设置自增值 (String, Object)
     *
     * @param key 键
     * @param obj 对象
     */
    public void incr(String key, Long obj) {
        try {
            redisTemplate.opsForValue().increment(key, obj);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 设置自增值 (String, Object)
     *
     * @param key 键
     * @param obj 对象
     */
    public Long incrBy(String key, Long obj) {
        try {
            return redisTemplate.opsForValue().increment(key, obj);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * Hash: 添加值
     *
     * @param key   键
     * @param field Hash 键
     * @param value 对象
     */
    public void hset(String key, String field, String value) {
        try {
            redisTemplate.opsForHash().put(key, field, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Hash: 添加 Hash 对象
     *
     * @param key 键
     * @param map Hash 对象
     */
    public void hsetAll(String key, Map map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Hash: 获取所有值
     *
     * @param key 键
     * @return Map 对象
     */
    public Map<Object, Object> entries(String key) {
        try {
            return redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Hash: 获取指定 field 值
     *
     * @param key   键
     * @param field Hash 键
     * @return Map 对象
     */
    public String hget(String key, String field) {
        try {
            Object result = redisTemplate.opsForHash().get(key, field);
            if (result != null) {
                return result.toString();
            } else {
                return "";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "";
        }
    }

    /**
     * Hash: 删除指定 key 的字段值
     *
     * @param key   键
     * @param field Hash 键
     * @return Map 对象
     */
    public void hdel(String key, String field) {
        try {
            redisTemplate.opsForHash().delete(key, field);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Hash: 删除指定 key 的字段值
     *
     * @param key 键
     */
    public void del(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 组合键
     *
     * @param prefix 前缀
     * @param key    键
     * @return 结果
     */
    public String join(String prefix, String key) {
        StringBuffer sb = new StringBuffer();
        sb.append(prefix);
        sb.append(COLON);
        sb.append(key);
        return sb.toString();
    }

    /**
     * 组合键
     *
     * @param keys 数组
     * @return 结果
     */
    public String join(String... keys) {
        StringBuffer sb = new StringBuffer();
        for (String str : keys) {
            if (Objects.equals(str, "")) {
                continue;
            }
            sb.append(str);
            sb.append(COLON);
        }
        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }

    /**
     * 只有在 key 不存在时设置 key 的值
     *
     * @param key
     * @param value
     * @return 不存在返回 true， 存在返回 false
     */
    public boolean setNX(String key, String value) {
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        return connection.setNX(key.getBytes(), value.getBytes());
    }

    /**
     * 只有在 key 不存在时设置 key 的值
     *
     * @param key
     * @param value
     * @param expireSec 过期时间（秒）
     * @return
     */
    public boolean setNX(String key, String value, Long expireSec) {
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        Object result = connection.execute("SET", key.getBytes(), value.getBytes(), "EX".getBytes(), expireSec.toString().getBytes(), "NX".getBytes());
        if (result == null) {
            return false;
        }

        if (result instanceof String) {
            return "OK".equalsIgnoreCase((String) result);
        } else if (result instanceof Byte[] || result instanceof byte[]) {
            byte[] obj = (byte[]) result;
            return "OK".equalsIgnoreCase(new String(obj));
        }
        throw new RuntimeException("setNX返回值无法识别.result:" + result);
    }

    /**
     * 为给定 key 设置过期时间
     *
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    public boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    public void deleteByKeys(Set<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * Set 添加元素
     *
     * @param key
     * @param value
     * @return
     */
    public boolean sadd(String key, String... value) {
        Long result = 0L;
        try {
            result = redisTemplate.opsForSet().add(key, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result == 0 ? false : true;
    }

    /**
     * 批量清楚以 key 开头的数据
     *
     * @param key
     */
    public void batchDel(String key) {
        Set<String> keys = keys(key + "*");
        deleteByKeys(keys);
    }

    /**
     * 设置 map 中 key 过期时间
     *
     * @param key     map
     * @param timeout 过期时间
     * @param unit    单位
     */
    public void expireMap(String key, long timeout, TimeUnit unit) {
        redisTemplate.boundHashOps(key).expire(timeout, unit);
    }

}
