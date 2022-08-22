package com.intern.common.dao.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserProfile implements Serializable {
    String username;
    String password;

}
