package com.intern.common.mapper.Sql;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;


public class UserProfileMapperSql {
    public String checkUsernameExistSql(String username) {
        return new SQL() {{
            SELECT("count(*)");
            FROM("user_profile");
            WHERE("username=#{username}");
            LIMIT("1");
        }}.toString();
    }

    public String insertUserSql(String username, String password){
        return new SQL(){{
            INSERT_INTO("user_profile");
            VALUES("username, password","#{username}, #{password}");
        }}.toString();
    }

    public String getPasswordSql(String username){
        return new SQL(){{
            SELECT("password");
            FROM("user_profile");
            WHERE("username=#{username}");
        }}.toString();
    }

    public String deleteUserSql(String username){
        return new SQL(){{
            DELETE_FROM("user_profile");
            WHERE("username=#{username}");
        }}.toString();
    }

    public String getAuthoritySql(String username){
        return new SQL(){{
            SELECT("user_authority");
            FROM("user_profile");
            WHERE("username=#{username}");
        }}.toString();
    }

    public String updateEmailSql(String username,String userEmail){
        return new SQL(){{
            UPDATE("user_profile");
            SET("user_email=#{userEmail}");
            WHERE("username=#{username}");
        }}.toString();
    }

    public String updateAuthoritySql(String username, String authority){
        return new SQL(){{
            UPDATE("user_profile");
            SET("user_authority=#{authority}");
            WHERE("username=#{username}");
        }}.toString();
    }
}

