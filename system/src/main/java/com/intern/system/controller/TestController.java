package com.intern.system.controller;

import com.intern.common.dao.pojo.JsonResult;
import com.intern.tools.utils.RedisUtil;
import com.intern.tools.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class TestController {
    @Autowired
    StringUtil stringUtil;
    @Autowired
    RedisUtil redisUtil;


    @PostMapping("/test")
    public JsonResult test(String s){

        throw new RuntimeException("error");
//        return JsonResult.success("logging.test");
    }

    @PostMapping("/redis")
    public JsonResult redis(String key,String value){
        redisUtil.setString(key,value);
        return JsonResult.success("redis");
    }
}

