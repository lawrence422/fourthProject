package com.intern.common.dao.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Dictionary implements Serializable {
    private int dict_id;
    private String dict_name;
    private String dict_authority;
    private String dict_description;

}
