package com.java2023.exam12.bproject.dao;

import com.java2023.exam12.bproject.dto.UserRoleDTO;
import com.mybatisflex.core.BaseMapper;
import com.java2023.exam12.bproject.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色表(SysRole)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-14 17:05:47
 */
public interface SysRoleDao extends BaseMapper<SysRole> {
    List<SysRole> findRoleByRname(@Param("rname") String rname, @Param("page") Integer page, @Param("size") Integer size);
    List<SysRole> findAllRoleByRname(@Param("rname") String rname);
}

