package com.java2023.exam12.bproject.service;

import com.java2023.exam12.bproject.entity.SysRole;
import com.java2023.exam12.bproject.entity.SysUser;
import com.java2023.exam12.bproject.vo.RoleVo;
import com.mybatisflex.core.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
public interface RoleService {
    public void add(SysRole role);

    public Map<String,Object> query(String username, Integer pageIndex, Integer pageSize);

    public void deleteById(Integer rid);


    public void grantPerm(RoleVo roleVo);

    RoleVo getRole(Integer rid);
}
