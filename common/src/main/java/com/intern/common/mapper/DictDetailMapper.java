package com.intern.common.mapper;

import com.intern.common.dao.pojo.DictionaryDetail;
import com.intern.common.mapper.Sql.DictDetailMapperSql;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface DictDetailMapper {

    @InsertProvider(type = DictDetailMapperSql.class,method = "insertDictDetailSql")
    int insertDictDetail(DictionaryDetail dictionaryDetail);

    @SelectProvider(type = DictDetailMapperSql.class,method = "getDictDetailWithPageSql")
    List<DictionaryDetail> getDictDetailWithPage(int dataPerPage, int data);
}
