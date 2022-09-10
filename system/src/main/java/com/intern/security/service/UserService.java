package com.intern.security.service;



import com.intern.common.dao.pojo.JsonResult;
import com.intern.common.dao.UserProfile;

import java.util.Map;

@SuppressWarnings({"rawtypes"})
public interface UserService {
    JsonResult insertUser(UserProfile userProfile);

    JsonResult login(UserProfile userProfile);

    JsonResult deleteUser();

    JsonResult logout(Map<String, String> request);

    JsonResult insertEmail(String email);

    JsonResult setAuthority(String name, String authority);
}