package com.intern.common.mapper.Sql;

import com.intern.common.dao.pojo.Department;
import org.apache.ibatis.jdbc.SQL;

public class DeptMapperSql {

    public String insertDepartmentSql(Department department){
        return new SQL(){{
            INSERT_INTO("department");
            VALUES("dept_name,dept_detail","#{dept_name},#{dept_detail}");
        }}.toString();
    }

    public String getDeptByPageSql(int dataPerPage,int data){
        return new SQL(){{
            SELECT("*");
            FROM("department");
            LIMIT("#{dataPerPage}");
            OFFSET("#{data}");
        }}.toString();
    }

}
