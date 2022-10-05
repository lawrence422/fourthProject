package com.intern.system.service.impl;

import com.intern.common.dao.pojo.Dictionary;
import com.intern.common.dto.JsonResult;
import com.intern.common.mapper.DictMapper;
import com.intern.system.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl implements DictService {
    @Autowired
    DictMapper dictMapper;

    @Override
    public JsonResult insertDictionary(Dictionary dictionary) {
        if (dictMapper.insertDictionary(dictionary)==1){
            return JsonResult.success("Successfully insert dictionary.");
        }else {
            return JsonResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @Override
    public JsonResult getDictionaryByPage(int dataPerPage, int page) {
        List<Dictionary> list= dictMapper.getDictionaryByPage(dataPerPage,dataPerPage*page);
        return JsonResult.success(list);
    }


}
