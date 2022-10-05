package com.intern.system.controller;

import com.intern.common.dto.JsonResult;
import com.intern.logging.annotation.SystemLogger;
import com.intern.system.service.SystemService;
import com.intern.tools.utils.RedisUtil;
import com.intern.tools.utils.StringUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api( tags = {"test"})
@RestController
@RequestMapping("/system")
@SuppressWarnings("rawtypes")
public class SystemController {
    @Autowired
    StringUtil stringUtil;
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    SystemService systemService;

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

//    @PostMapping("/account")
//    @SystemLogger()
//    public JsonResult logging(@RequestBody UserProfile userProfile){
//        return systemService.logging(userProfile);
//    }

    @PostMapping("/register")
    @SystemLogger()
    public JsonResult register(String username,String password){
        return systemService.register(username, password);
    }

    @PreAuthorize("hasAnyAuthority('normal','admin')")
    @GetMapping("/getTest")
    @SystemLogger()
    public JsonResult getTest(){
        return JsonResult.success("getTest");
    }

    @PostMapping("/getVerificationCode")
    @SystemLogger()
    public JsonResult getVerificationCode(String username){
        return systemService.getVerificationCode(username);
    }

    @PreAuthorize("hasAnyAuthority('normal','admin')")
    @GetMapping("/getUserProfile")
    @SystemLogger()
    public JsonResult getUserProfile(){
        return systemService.getUserProfile();
    }

}

