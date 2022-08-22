package com.intern.logging.controller;

import com.intern.common.dao.pojo.JsonResult;
import com.intern.common.dao.pojo.UserProfile;
import com.intern.logging.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("logging")
public class LoggingController {
  @Autowired
    LoggingService loggingService;

    @GetMapping("test")
    public JsonResult test(){
        return JsonResult.success("logging.test");
    }


    @PostMapping("/account")
    public JsonResult logging(@RequestBody UserProfile userProfile){
        return loggingService.logging(userProfile);

    }
}
