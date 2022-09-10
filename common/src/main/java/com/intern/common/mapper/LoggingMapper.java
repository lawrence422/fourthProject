package com.intern.common.mapper;

import com.intern.common.dao.SystemLog;
import com.intern.common.mapper.Sql.LoggingMapperSql;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoggingMapper {

    @InsertProvider(type = LoggingMapperSql.class,method = "insertSystemLog")
    void insertSystemLog(SystemLog systemLog);

    @InsertProvider(type = LoggingMapperSql.class,method = "insertExceptionLog")
    void insertExceptionLog(SystemLog systemLog);
}
