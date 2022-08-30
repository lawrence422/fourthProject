package com.intern.logging.service;

import com.intern.common.dao.pojo.JsonResult;
import com.intern.common.dao.pojo.SystemLog;
import com.intern.common.dao.pojo.UserProfile;

public interface LoggingService {
    void insertSystemLog(SystemLog systemLog);

    void insertExceptionLog(SystemLog systemLog);
}
