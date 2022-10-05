package com.intern.system.controller;

import com.intern.common.dao.pojo.Department;
import com.intern.common.dto.JsonResult;
import com.intern.logging.annotation.SystemLogger;
import com.intern.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DeptController {
    @Autowired
    DeptService deptService;

//    @PreAuthorize("hasAnyAuthority('admin','normal')")
    @SystemLogger()
    @PostMapping("/insert")
    public JsonResult insertDepartment(@RequestBody Department department){
        return deptService.insertDepartment(department);
    }

    @SystemLogger
    @PostMapping("/get")
    public JsonResult getDeptByPage(int dataPerPage,int page){
        return deptService.getDeptByPage(dataPerPage,page);
    }

}
