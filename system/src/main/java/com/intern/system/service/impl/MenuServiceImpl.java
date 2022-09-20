package com.intern.system.service.impl;

import com.intern.common.dao.Menu;
import com.intern.common.dao.SystemLog;
import com.intern.common.dao.pojo.JsonResult;
import com.intern.common.mapper.MenuMapper;
import com.intern.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@SuppressWarnings("rawtypes")
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;


    @Override
    public JsonResult addMenu(Menu menu) {
        int temp = 0;
        temp = menuMapper.addMenu(menu);
        if (temp == 1) {
            return JsonResult.success("Insert Success");
        }
        return JsonResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @Override
    public JsonResult changeParent(String parentName, String menuName) {
        int temp = 0;
        temp = menuMapper.updateParent(parentName, menuName);
        if (temp == 1) {
            return JsonResult.success(parentName);
        }
        return JsonResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @Override
    public JsonResult updateAccessAuthority(String menuName, String menuAccessAuthority) {
        int temp=0;
        temp= menuMapper.updateAccessAuthority(menuName,menuAccessAuthority);
        if (temp==1){
            return JsonResult.success("update Success");
        }
        return JsonResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @Override
    public JsonResult deleteMenu(String menuName) {
        List<Menu> list=new ArrayList<>();
        list= menuMapper.getChild(menuName);
        if (list.size()!=0){
            String parentName=menuMapper.getParentName(menuName);
            String newParent=list.get(0).getMenuName();
            menuMapper.updateParent(parentName,newParent);
            for(int i=1;i<list.size();i++){
                menuMapper.updateParent(newParent,list.get(i).getMenuName());
            }
        }
         int temp= menuMapper.deleteMenu(menuName);
        if (temp==1){
            return JsonResult.success("Delete Success");
        }
        return JsonResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

    }

    @Override
    public JsonResult getMenu() {
        String temp = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        List<Menu> list = new ArrayList<>();
        if ("admin".equals(temp.substring(1,temp.length()-1))) {
            list = menuMapper.getAllMenu();
        } else {
            list = menuMapper.getNormalMenu();
        }
        if (list.size() != 0) {
            Map<String, List<Menu>> map = new HashMap<>();
            for (Menu menu : list) {
                if (menu!=null&& menu.getParentName() != null) {
                    if (map.containsKey(menu.getParentName())) {
                        map.get(menu.getParentName()).add(menu);
                    } else {
                        map.put(menu.getParentName(), new ArrayList<>(Collections.singletonList(menu)));
                    }
                } else {
                    map.put("-1", new ArrayList<>(Collections.singletonList(menu)));
                }
            }
        }
        return JsonResult.success(list);
    }

    @Override
    public JsonResult getChild(String menuName) {
        List<String> list=menuMapper.getChildName(menuName);
        return JsonResult.success(list);
    }

    @Override
    public JsonResult getSiblingAndParent(String menuName) {
        String parentName=menuMapper.getParentName(menuName);
        List<Menu>list= menuMapper.getChild(parentName);
        list.add(menuMapper.getMenu(parentName));
        return JsonResult.success(list);
    }

}
