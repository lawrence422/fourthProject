package com.intern.tools.utils;

import com.intern.common.variable.SystemVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {


    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    public boolean setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        stringRedisTemplate.expire(key, SystemVariable.REDIS_KEY_DURATION_IN_MINUTE, TimeUnit.MINUTES);
        return true;
    }

    public boolean setToken(String username, String token) {
        stringRedisTemplate.opsForHash().put("Token",username, token);
        return true;
    }

    public String getToken(String username) {
        return Objects.requireNonNull(stringRedisTemplate.opsForHash().get("Token", username)).toString();

    }

    public boolean deleteToken(String username) {
        stringRedisTemplate.opsForHash().delete("Token",username);
        return true;
    }

    public String getString(String key) {
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
            return stringRedisTemplate.opsForValue().get(key);
        }
        return null;
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
