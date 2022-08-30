package com.intern.system.controller;

import com.intern.common.dao.pojo.JsonResult;
import com.intern.common.dao.pojo.UserProfile;
import com.intern.logging.annotation.SystemLogger;
import com.intern.system.service.SystemService;
import com.intern.tools.utils.RedisUtil;
import com.intern.tools.utils.StringUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@Api( tags = {"test"})
@RestController
@RequestMapping("/system")
public class SystemController {
    @Autowired
    StringUtil stringUtil;
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    SystemService systemService;

    /***
     * Test Error can be handled and logged correctly
     * @param s
     * @return
     */
    @PostMapping("/test")
    @SystemLogger()
    public JsonResult test(String s){

        throw new RuntimeException("error");
//        return JsonResult.success("logging.test");
    }

    /***
     * Test server can connect to redis correctly
     * @param key
     * @param value
     * @return
     */
    @PostMapping("/redis")
    @SystemLogger()
    public JsonResult redis(String key,String value){
        redisUtil.setString(key,value);
        return JsonResult.success("redis");
    }

    /***
     * Test parameter can be logged correctly
     * @param s
     * @return
     */
    @PostMapping("/test2")
    @SystemLogger()
    public JsonResult test2(String s){
        return JsonResult.success("logging.test");
    }

    @PostMapping("/account")
    @SystemLogger()
    public JsonResult logging(@RequestBody UserProfile userProfile){
        return systemService.logging(userProfile);

    }
}

