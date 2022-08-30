package com.intern.common.handler;

import com.intern.common.dao.pojo.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    Logger logger= LoggerFactory.getLogger(RestExceptionHandler.class);
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult<String> exception(Exception e){
        logger.error("Error");
        return JsonResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());

    }
}
