package com.intern.common;

import com.intern.common.dao.pojo.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class controller {
    Logger logger= LoggerFactory.getLogger(controller.class);
    @GetMapping("/test")
    public JsonResult test(){
        logger.info("test");
        return JsonResult.success("test");
    }
}
