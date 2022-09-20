package com.intern.system.controller;

import com.intern.common.dao.Menu;
import com.intern.common.dao.pojo.JsonResult;
import com.intern.logging.annotation.SystemLogger;
import com.intern.system.service.MenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"menu"})
@RestController
@RequestMapping("/menu")
@SuppressWarnings("rawtypes")
public class MenuController {
    @Autowired
    MenuService menuService;

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("/addMenu")
    @SystemLogger()
    public JsonResult addMenu( @RequestBody Menu menu){
        return menuService.addMenu(menu);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("/changeParent")
    @SystemLogger()
    public JsonResult changeParent(String parentName,String menuName){
        return menuService.changeParent(parentName,menuName);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("/changeAccessAuthority")
    @SystemLogger()
    public JsonResult changeAccessAuthority(String menuName,String authority){
        return menuService.updateAccessAuthority(menuName,authority);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("/deleteMenu")
    @SystemLogger()
    public JsonResult deleteMenu(String menuName){
        return menuService.deleteMenu(menuName);
    }


    @PreAuthorize("hasAnyAuthority('admin','normal')")
    @GetMapping("/getMenu")
    @SystemLogger()
    public JsonResult getMenu(){
        return menuService.getMenu();
    }

    @PreAuthorize("hasAnyAuthority('admin','normal')")
    @PostMapping("/getChild")
    @SystemLogger()
    public JsonResult getChild(String menuName){
        return menuService.getChild(menuName);
    }

    @PreAuthorize("hasAnyAuthority('admin','normal')")
    @PostMapping("/getSiblingAndParent")
    @SystemLogger()
    public JsonResult getSiblingAndParent(String menuName){
        return menuService.getSiblingAndParent(menuName);
    }
}
