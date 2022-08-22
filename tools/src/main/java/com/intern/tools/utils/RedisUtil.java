package com.intern.tools.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RedisUtil {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    public boolean setString(String key, String value) {

        stringRedisTemplate.opsForValue().set(key, value);
        return true;
    }

    public String getString(String key, String value) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public boolean multiSet(Map<String,String>map){
        stringRedisTemplate.opsForValue().multiSet(map);
        return true;
    }

    public List<String> multiGet(List<String>keyList){
        return stringRedisTemplate.opsForValue().multiGet(keyList);
    }

    public void appendString(String key, String string){
        stringRedisTemplate.opsForValue().append(key,string);
    }
}
