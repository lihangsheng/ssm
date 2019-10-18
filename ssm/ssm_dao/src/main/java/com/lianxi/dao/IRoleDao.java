package com.lianxi.dao;

import com.lianxi.domain.Permission;
import com.lianxi.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IRoleDao {
    //根据用户id查询所有的角色
    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")


    public List<Role> findRoleByUserId(String userId) throws Exception;

    @Select("select * from role")
    List<Role> findAll() throws Exception;
     @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role) throws Exception;
     @Select("select * from role where id=#{roleId}")
    Role findById(String roleId) throws Exception;
     @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(String roleId);
    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId")String roleId,@Param("permissionId") String permissionId);
}
