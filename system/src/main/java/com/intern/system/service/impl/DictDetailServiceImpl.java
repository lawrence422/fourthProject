package com.intern.system.service.impl;

import com.intern.common.dao.pojo.DictionaryDetail;
import com.intern.common.dto.JsonResult;
import com.intern.common.mapper.DictDetailMapper;
import com.intern.common.mapper.DictMapper;
import com.intern.system.service.DictDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictDetailServiceImpl implements DictDetailService {
    @Autowired
    DictMapper dictMapper;
    @Autowired
    DictDetailMapper dictDetailMapper;

    @Override
    public JsonResult insertDictDetial(String dictname, DictionaryDetail dictionaryDetail) {
        int dictId=Integer.parseInt(dictMapper.getDictionaryIdByName(dictname));
        dictionaryDetail.setDetail_id(dictId);

        if (dictDetailMapper.insertDictDetail(dictionaryDetail)==1){
            return JsonResult.success("Succesfully insert dictionary detail");
        }else {
            return JsonResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.URI_TOO_LONG.getReasonPhrase());
        }
    }

    @Override
    public JsonResult getDictDetailWithPage(int dataPerPage, int page) {
        List<DictionaryDetail> list= dictDetailMapper.getDictDetailWithPage(dataPerPage,page*dataPerPage);
        return JsonResult.success(list);

    }
}
