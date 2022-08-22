package com.intern.tools.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

@Component
public class ArrayUtil<T> {
    public T[] clone(T[]array){
        return ArrayUtils.clone(array);
    }

    public void reverse(T[]array){
        ArrayUtils.reverse(array);
    }
}
