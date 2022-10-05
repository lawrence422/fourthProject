package com.intern.system.service;

import com.intern.common.dao.pojo.DictionaryDetail;
import com.intern.common.dto.JsonResult;

public interface DictDetailService {

    JsonResult insertDictDetial(String dictname, DictionaryDetail dictionaryDetail);

    JsonResult getDictDetailWithPage(int dataPerPage, int page);
}
