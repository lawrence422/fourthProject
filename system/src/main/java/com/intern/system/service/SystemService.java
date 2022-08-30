package com.intern.system.service;

import com.intern.common.dao.pojo.JsonResult;
import com.intern.common.dao.pojo.UserProfile;

public interface SystemService {
    JsonResult logging(UserProfile userProfile);
}
