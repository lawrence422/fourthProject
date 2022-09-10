package com.intern.logging.service;

import com.intern.common.dao.SystemLog;

public interface LoggingService {
    void insertSystemLog(SystemLog systemLog);

    void insertExceptionLog(SystemLog systemLog);
}
