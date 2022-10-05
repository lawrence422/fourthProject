package com.intern.common.dao.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Department implements Serializable {
    private int dept_id;
    private String dept_name;
    private String dept_detail;
}
