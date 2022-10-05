package com.intern.common.mapper.Sql;

import com.intern.common.dao.pojo.DictionaryDetail;
import org.apache.ibatis.jdbc.SQL;

public class DictDetailMapperSql {
    public String insertDictDetailSql(DictionaryDetail dictionaryDetail){
        return new SQL(){{
            INSERT_INTO("dictionary_detail");
            VALUES("dict_id,detail_name","#{dict_id},#{detail_name}");
        }}.toString();
    }

    public String getDictDetailWithPageSql(int dataPerPage, int data){
        return new SQL(){{
            SELECT("*");
            FROM("dictionary_detail");
            LIMIT("#{dataPerPage}");
            OFFSET("#{data}");
        }}.toString();
    }



}
