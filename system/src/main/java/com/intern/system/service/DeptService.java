package com.intern.system.service;

import com.intern.common.dao.pojo.Department;
import com.intern.common.dto.JsonResult;

public interface DeptService {
    JsonResult insertDepartment(Department department);

    JsonResult getDeptByPage(int dataPerPage, int page);
}
