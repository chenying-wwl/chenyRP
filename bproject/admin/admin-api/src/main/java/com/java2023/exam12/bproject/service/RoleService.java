package com.java2023.exam12.bproject.service;

import com.java2023.exam12.bproject.entity.SysRole;

import java.util.List;
import java.util.Map;

public interface RoleService {
    public List<SysRole> getAllRole();
    public Map<String,Object> query(String username, Integer pageIndex, Integer pageSize);
}
