package com.intern.logging.service;

import com.intern.common.dao.pojo.JsonResult;
import com.intern.common.dao.pojo.UserProfile;

public interface LoggingService {
    JsonResult logging(UserProfile userProfile);
}
