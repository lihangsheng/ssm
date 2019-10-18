package com.lianxi.service;

import com.lianxi.domain.Permission;
import com.lianxi.domain.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    Role findById(String roleId) throws Exception;

    List<Permission> findOtherPermissions(String roleId) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
}
