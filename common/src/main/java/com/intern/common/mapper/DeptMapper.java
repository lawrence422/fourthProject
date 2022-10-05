package com.intern.common.mapper;

import com.intern.common.dao.pojo.Department;
import com.intern.common.mapper.Sql.DeptMapperSql;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface DeptMapper {

    @InsertProvider(type = DeptMapperSql.class,method = "insertDepartmentSql")
    int insertDepartment(Department department);

    @SelectProvider(type = DeptMapperSql.class,method = "getDeptByPageSql")
    List<Department> getDeptByPage(int dataPerPage, int data);
}
