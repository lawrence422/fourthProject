package com.intern.system.service.impl;

import com.intern.common.dao.pojo.JsonResult;
import com.intern.common.dao.pojo.UserProfile;
import com.intern.common.mapper.SystemMapper;
import com.intern.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    SystemMapper systemMapper;
    @Override
    public JsonResult logging(UserProfile userProfile) {
        if (systemMapper.logIn(userProfile)!=0) {
            return JsonResult.success(userProfile);
        }else{
            return JsonResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }
}
