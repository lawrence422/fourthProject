package com.intern.common.mapper;

import com.intern.common.dao.Menu;
import com.intern.common.mapper.Sql.MenuMapperSql;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MenuMapper {
    @InsertProvider(type = MenuMapperSql.class,method = "addMenuSql")
    int addMenu(Menu menu);

    @UpdateProvider(type = MenuMapperSql.class,method = "updateParentSql")
    int updateParent(String parentName, String menuName);

    @UpdateProvider(type = MenuMapperSql.class,method = "updateAccessAuthority")
    int updateAccessAuthority(String menuName, String menuAccessAuthority);

    @DeleteProvider(type = MenuMapperSql.class,method = "deleteMenuSql")
    int deleteMenu(String menuName);

    @SelectProvider(type = MenuMapperSql.class,method = "getAllMenuSql")
    List<Menu> getAllMenu();

    @SelectProvider(type = MenuMapperSql.class,method = "getNormalMenuSql")
    List<Menu> getNormalMenu();

    @SelectProvider(type = MenuMapperSql.class,method = "getChildSql")
    List<Menu> getChild(String menuName);

    @SelectProvider(type = MenuMapperSql.class,method = "getParentNameSql")
    String getParentName(String menuName);

    @SelectProvider(type = MenuMapperSql.class,method = "getChildNameSql")
    List<String> getChildName(String menuName);

    @SelectProvider(type = MenuMapperSql.class,method = "getMenuSql")
    Menu getMenu(String menuName);
}
