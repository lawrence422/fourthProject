package com.intern.system.service.impl;

import com.intern.common.dto.JsonResult;
import com.intern.common.dao.UserProfile;
import com.intern.common.mapper.UserProfileMapper;
import com.intern.common.variable.SystemVariable;
import com.intern.security.service.JwtService;
import com.intern.system.service.SystemService;
import com.intern.tools.utils.RandomUtil;
import com.intern.tools.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@SuppressWarnings("rawtypes")
public class SystemServiceImpl implements SystemService {
    @Autowired
    UserProfileMapper userProfileMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    JwtService jwtService;


    @Override
    public JsonResult logging(UserProfile userProfile) {
        String username=userProfile.getUsername();
        if (userProfileMapper.checkUsernameExist(username)!=0&&userProfileMapper.getPassword(username).equals(userProfile.getPassword())) {
            String token= jwtService.generateToken(userProfile);
            Authentication authentication=new UsernamePasswordAuthenticationToken(userProfile.getUsername(),userProfile.getPassword());
            authenticationManager.authenticate(authentication);
            Map<String,String> map= Collections.singletonMap("token",token);
            return JsonResult.success(map);
        }else{
            return JsonResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @Override
    public JsonResult register(String username, String password) {
        if (userProfileMapper.checkUsernameExist(username)!=0){
            return JsonResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(),"username exists, please change another username");
        }
        int temp= userProfileMapper.insertUser(username,new BCryptPasswordEncoder().encode(password));
        if(temp==1){
            return JsonResult.success("Create account successfully.");
        }else {
            return JsonResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @Override
    public JsonResult getVerificationCode(String username) {
        String temp= RandomUtil.createVerificationCode();
//        System.out.println(temp+", "+username);
        if (redisUtil.setString(username,temp)) {
            Map<String,String>map=new LinkedHashMap<>();
            map.put("username",username);
            map.put("verificationCode",temp);
            map.put("expired", Integer.toString(SystemVariable.REDIS_KEY_DURATION_IN_MINUTE));
            return JsonResult.success(map);
        }else {
            return JsonResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }

    }

    @Override
    public JsonResult getUserProfile() {
        return JsonResult.success(SecurityContextHolder.getContext());
    }
}
