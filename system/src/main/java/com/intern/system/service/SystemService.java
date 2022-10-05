package com.intern.system.service;

import com.intern.common.dto.JsonResult;
import com.intern.common.dao.UserProfile;

public interface SystemService {
    JsonResult logging(UserProfile userProfile);

    JsonResult register(String username,String password);

    JsonResult getVerificationCode(String username);

    JsonResult getUserProfile();
}
