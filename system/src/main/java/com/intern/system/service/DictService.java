package com.intern.system.service;

import com.intern.common.dao.pojo.Dictionary;
import com.intern.common.dto.JsonResult;

public interface DictService {
    JsonResult insertDictionary(Dictionary dictionary);

    JsonResult getDictionaryByPage(int dataPerPage, int page);
}
