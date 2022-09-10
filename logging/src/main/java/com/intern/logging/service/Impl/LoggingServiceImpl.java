package com.intern.logging.service.Impl;

import com.intern.common.dao.SystemLog;
import com.intern.common.mapper.LoggingMapper;
import com.intern.logging.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggingServiceImpl implements LoggingService {
    @Autowired
    LoggingMapper loggingMapper;

    @Override
    public void insertSystemLog(SystemLog systemLog) {
        loggingMapper.insertSystemLog(systemLog);
    }

    @Override
    public void insertExceptionLog(SystemLog systemLog) {
        loggingMapper.insertExceptionLog(systemLog);
    }
}
