package com.intern.system.controller;

import com.intern.common.dao.pojo.DictionaryDetail;
import com.intern.common.dto.JsonResult;
import com.intern.system.service.DictDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dictionaryDetail")
public class DictDetailController {
    @Autowired
    DictDetailService dictDetailService;


    @PostMapping("/insert")
    public JsonResult insertDictDetail(String dictname, @RequestBody DictionaryDetail dictionaryDetail){
        System.out.println(dictname+" "+ dictionaryDetail.getDetail_name());
        return dictDetailService.insertDictDetial(dictname,dictionaryDetail);
    }

    @PostMapping("/get")
    public JsonResult getDictDetailByPage(int dataPerPage, int page){
        return dictDetailService.getDictDetailWithPage(dataPerPage,page);
    }
}
