package com.java2023.exam12.bproject.dto;

import com.java2023.exam12.bproject.entity.SysPermission;
import com.java2023.exam12.bproject.entity.SysRole;
import com.java2023.exam12.bproject.entity.SysUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
@Setter
@Getter
public class UserDTO extends SysUser {
    List<SysPermission> permissions;
}
