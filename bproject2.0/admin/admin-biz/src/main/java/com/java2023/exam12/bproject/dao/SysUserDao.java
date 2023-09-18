package com.java2023.exam12.bproject.dao;

import com.java2023.exam12.bproject.dto.UserDTO;
import com.java2023.exam12.bproject.dto.UserRoleDTO;
import com.java2023.exam12.bproject.vo.UserVo;
import com.mybatisflex.core.BaseMapper;
import com.java2023.exam12.bproject.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表(SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-14 17:05:48
 */
public interface SysUserDao extends BaseMapper<SysUser> {

    UserDTO findUserByNameWithPermissions(String userName);
    List<UserDTO> findUserAndRole(@Param("username") String username, @Param("page") Integer page, @Param("size") Integer size);
    List<UserDTO> findAllUserByUsername(@Param("username") String username);
}

