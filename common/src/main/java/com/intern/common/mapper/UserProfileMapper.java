package com.intern.common.mapper;


import com.intern.common.mapper.Sql.UserProfileMapperSql;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserProfileMapper {
    @SelectProvider(type = UserProfileMapperSql.class,method = "checkUsernameExistSql")
    int checkUsernameExist(String username);

    @InsertProvider(type = UserProfileMapperSql.class,method = "insertUserSql")
    int insertUser(String username,String password);

    @UpdateProvider(type = UserProfileMapperSql.class,method = "updateEmailSql")
    int updatetEmail(String username, String userEmail);

    @SelectProvider(type = UserProfileMapperSql.class,method="getPasswordSql")
    String getPassword(String username);

    @DeleteProvider(type = UserProfileMapperSql.class,method = "deleteUserSql")
    int deleteUser(String username);

    @SelectProvider(type = UserProfileMapperSql.class,method = "getAuthoritySql")
    String getAuthority(String username);

    @InsertProvider(type = UserProfileMapperSql.class,method = "updateAuthoritySql")
    int updateAuthority(String username, String authority);

}
