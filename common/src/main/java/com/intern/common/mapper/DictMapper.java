package com.intern.common.mapper;

import com.intern.common.dao.pojo.Dictionary;
import com.intern.common.dto.JsonResult;
import com.intern.common.mapper.Sql.DictMapperSql;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface DictMapper {
    @InsertProvider(type = DictMapperSql.class,method = "insertDictionarySql")
    int insertDictionary(Dictionary dictionary);

    @SelectProvider(type = DictMapperSql.class,method = "getDictionaryIdByNameSql")
    String getDictionaryIdByName(String dict);

    @SelectProvider(type = DictMapperSql.class,method = "getDictionaryByPageSql")
    List<Dictionary> getDictionaryByPage(int dataPerPage, int data);
}
