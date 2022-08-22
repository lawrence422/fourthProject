package com.intern.tools.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class StringUtil {
    public boolean isAnyBlank(String ... strings){
        return StringUtils.isAnyBlank(strings);
    }


}
