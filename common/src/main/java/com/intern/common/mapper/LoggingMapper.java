package com.intern.common.mapper;

import com.intern.common.dao.pojo.UserProfile;
import com.intern.common.mapper.Sql.LoggingMapperSql;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface LoggingMapper {
    @SelectProvider (type = LoggingMapperSql.class, method = "loggingSql")
    int logging(UserProfile userProfile);
}
