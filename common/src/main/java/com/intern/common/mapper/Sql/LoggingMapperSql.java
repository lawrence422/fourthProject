package com.intern.common.mapper.Sql;


import com.intern.common.dao.pojo.SystemLog;
import com.intern.common.dao.pojo.UserProfile;
import org.apache.ibatis.jdbc.SQL;

public class LoggingMapperSql {


    public String insertSystemLog(SystemLog systemLog) {
        return new SQL() {{
            INSERT_INTO("system_log");
            VALUES("sys_module, sys_type, sys_desc, sys_param, sys_response, sys_method, sys_ip, sys_date", "#{sysModule},#{sysType}, #{sysDesc}, #{sysParam}, #{sysResponse}, #{sysMethod}, #{sysIp} , #{sysDate}");
        }}.toString();
    }

    public String insertExceptionLog(SystemLog systemLog) {
        return new SQL() {{
            INSERT_INTO("exception_log");
            VALUES("sys_module, sys_type, sys_desc, sys_param, sys_response, sys_method, sys_ip, sys_date", "#{sysModule},#{sysType}, #{sysDesc}, #{sysParam}, #{sysResponse}, #{sysMethod}, #{sysIp} , #{sysDate}");
        }}.toString();
    }
}
