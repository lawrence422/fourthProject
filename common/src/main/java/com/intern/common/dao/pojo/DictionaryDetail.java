package com.intern.common.dao.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class DictionaryDetail implements Serializable {
    private int detail_id;
    private int dict_id;
    private String detail_name;
    private int detail_active;
}
