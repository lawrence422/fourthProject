package com.intern.common.mapper.Sql;

import com.intern.common.dao.pojo.Menu;
import org.apache.ibatis.jdbc.SQL;

public class MenuMapperSql {
    public String addMenuSql(Menu menu){
        return new SQL(){{
            INSERT_INTO("menu");
            VALUES("menuName,menuAccessAuthority,menuHtmlLocation,parentName","#{menuName},#{menuAccessAuthority},#{menuHtmlLocation},#{parentName}");
        }}.toString();
    }

    public String updateParentSql(String parentName, String menuName){
        return new SQL(){{
            UPDATE("menu");
            SET("parentName=#{parentName}");
            WHERE("menuName=#{menuName}");
        }}.toString();
    }

    public String updateAccessAuthority(String menuName,String menuAccessAuthority){
        return new SQL(){{
            UPDATE("menu");
            SET("menuAccessAuthority=#{menuAccessAuthority}");
            WHERE("menuName=#{menuName}");
        }}.toString();
    }

    public String getAllMenuSql(){
        return new SQL(){{
            SELECT("*");
            FROM("menu");
        }}.toString();
    }

    public String getNormalMenuSql(){
        return new SQL(){{
            SELECT("*");
            FROM("menu");
            WHERE("menuAccessAuthority='normal'");
        }}.toString();
    }

    public String getChildSql(String menuName){
        return new SQL(){{
            SELECT("*");
            FROM("menu");
            WHERE("parentName=#{menuName}");
        }}.toString();
    }

    public String getParentNameSql(String menuName){
        return new SQL(){{
            SELECT("parentName");
            FROM("menu");
            WHERE("menuName=#{menuName}");
        }}.toString();
    }

    public String deleteMenuSql(String menuName){
        return new SQL(){{
            DELETE_FROM("menu");
            WHERE("menuName=#{menuName}");
        }}.toString();
    }

    public String getChildNameSql(String menuName){
        return new SQL(){{
            SELECT("menuName");
            FROM("menu");
            WHERE("parentName=#{menuName}");
        }}.toString();
    }

    public String getMenuSql(String menuName){
        return new SQL(){{
            SELECT("*");
            FROM("menu");
            WHERE("menuName=#{menuName}");
        }}.toString();
    }

}
