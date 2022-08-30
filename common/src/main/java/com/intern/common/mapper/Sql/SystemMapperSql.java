package com.intern.common.mapper.Sql;

import com.intern.common.dao.pojo.UserProfile;
import org.apache.ibatis.jdbc.SQL;

public class SystemMapperSql {
    public String logInSql(UserProfile userProfile) {
        return new SQL() {{
            SELECT("count(*)");
            FROM("test");
            WHERE("username=#{username}");
            AND();
            WHERE("password=#{password}");
        }}.toString();
    }
}
