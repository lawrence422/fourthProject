package com.intern.common.mapper;

import com.intern.common.dao.pojo.UserProfile;
import com.intern.common.mapper.Sql.SystemMapperSql;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface SystemMapper {
    @SelectProvider(type = SystemMapperSql.class, method = "logInSql")
    int logIn(UserProfile userProfile);
}
