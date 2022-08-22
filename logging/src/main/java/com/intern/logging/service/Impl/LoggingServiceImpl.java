package com.intern.logging.service.Impl;

import com.intern.common.dao.pojo.JsonResult;
import com.intern.common.dao.pojo.UserProfile;
import com.intern.common.mapper.LoggingMapper;
import com.intern.logging.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LoggingServiceImpl implements LoggingService {
    @Autowired
    LoggingMapper loggingMapper;

    @Override
    public JsonResult logging(UserProfile userProfile) {
        if (loggingMapper.logging(userProfile)!=0) {
            return JsonResult.success(userProfile);
        }else{
            return JsonResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }
}
