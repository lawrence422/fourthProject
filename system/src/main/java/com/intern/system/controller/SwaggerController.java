package com.intern.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swagger")
public class SwaggerController {
//    @RequestMapping(path = "/getStudent",method = RequestMethod.GET)
//    @ApiOperation("/根据学生id获取学生信息")
//    @ApiImplicitParam(name = "id",value = "id",required = true,paramType = "query",dataType = "int")
//    public Student getStudent(@RequestParam Integer id){
//        Student student = new Student();
//        student.setId(11);
//        student.setAge(21);
//        student.setName("全栈学习笔记");
//        Map<Integer,Student> studentMap = new HashMap<>();
//        studentMap.put(11,student);
//        return studentMap.get(id);
//    }
}
