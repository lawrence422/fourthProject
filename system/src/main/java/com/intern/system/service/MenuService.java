package com.intern.system.service;

import com.intern.common.dao.Menu;
import com.intern.common.dao.pojo.JsonResult;
@SuppressWarnings("rawtypes")
public interface MenuService {

    JsonResult addMenu( Menu menu);

    JsonResult changeParent(String parentName, String menuName);

    JsonResult updateAccessAuthority(String menuName, String authority);

    JsonResult deleteMenu(String menuName);

    JsonResult getMenu();

    JsonResult getChild(String menuName);

    JsonResult getSiblingAndParent(String menuName);
}
