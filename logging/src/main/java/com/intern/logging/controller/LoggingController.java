package com.intern.logging.controller;

import com.intern.common.dao.pojo.JsonResult;
import com.intern.common.dao.pojo.UserProfile;
import com.intern.logging.annotation.SystemLogger;
import com.intern.logging.service.LoggingService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"test"})
@RestController
@RequestMapping("logging")
public class LoggingController {
  @Autowired
    LoggingService loggingService;

    private static Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @GetMapping("test")
    @SystemLogger()
    public JsonResult test(){
        logger.info("test");
        return JsonResult.success("logging.test");
    }

}
