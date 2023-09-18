package com.java2023.exam12.bproject.service;

import com.java2023.exam12.bproject.dto.PermissionDTO;
import com.java2023.exam12.bproject.entity.SysPermission;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
public interface PermService {

    public void add(SysPermission permission);

    public Map<String, Object> query(Integer pageIndex, Integer pageSize);
    public List<PermissionDTO> list();

    public void deleteById(Integer id);

    public SysPermission findById(Integer id);
}
