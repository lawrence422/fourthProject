package com.intern.common.dao.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class Menu implements Serializable {
    String menuName;
    String parentName;
    String menuAccessAuthority;
    String menuHtmlLocation;
}
