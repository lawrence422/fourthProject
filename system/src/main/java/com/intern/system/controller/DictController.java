package com.intern.system.controller;

import com.intern.common.dao.pojo.Department;
import com.intern.common.dao.pojo.Dictionary;
import com.intern.common.dto.JsonResult;
import com.intern.logging.annotation.SystemLogger;
import com.intern.system.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dictionary")
public class DictController {
    @Autowired
    DictService dictService;


    @SystemLogger()
    @PostMapping("/insert")
    public JsonResult insertDictionary(@RequestBody Dictionary dictionary){
        return dictService.insertDictionary(dictionary);
    }

    @SystemLogger
    @PostMapping("/get")
    public JsonResult getDictionaryByPage(int dataPerPage,int page){
        return dictService.getDictionaryByPage(dataPerPage,page);
    }

}
