package com.intern.common.mapper.Sql;

import com.intern.common.dao.pojo.Dictionary;
import org.apache.ibatis.jdbc.SQL;

public class DictMapperSql {
    public String insertDictionarySql(Dictionary dictionary){
        return new SQL(){{
            INSERT_INTO("dictionary");
            VALUES("dict_name,dict_description","#{dict_name},#{dict_description}");
        }}.toString();
    }

    public String getDictionaryIdByNameSql(String dict){
        return new SQL(){{
            SELECT("dict_id");
            FROM("dictionary");
            WHERE("dict_name=#{dict}");
        }}.toString();
    }

    public String getDictionaryByPageSql(int dataPerPage,int data){
        return new SQL(){{
            SELECT("*");
            FROM("dictionary");
            LIMIT("#{dataPerPage}");
            OFFSET("#{data}");
        }}.toString();
    }
}
