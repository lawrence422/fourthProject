package com.intern.system.service.impl;

import com.intern.common.dao.pojo.Department;
import com.intern.common.dto.JsonResult;
import com.intern.common.mapper.DeptMapper;
import com.intern.system.service.DeptService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptMapper deptMapper;


    @Override
    public JsonResult insertDepartment(Department department) {
        if (deptMapper.insertDepartment(department)==1){
         return JsonResult.success("Successfully insert department.");
        }else {
            return JsonResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @Override
    public JsonResult getDeptByPage(int dataPerPage, int page) {
        List<Department>list=deptMapper.getDeptByPage(dataPerPage,dataPerPage*page);
        return JsonResult.success(list);
    }
}
