package com.lianxi.service;

import com.lianxi.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;
}
