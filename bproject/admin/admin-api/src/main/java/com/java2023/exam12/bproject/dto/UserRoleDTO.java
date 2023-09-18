package com.java2023.exam12.bproject.dto;

import com.java2023.exam12.bproject.entity.SysRole;
import com.java2023.exam12.bproject.entity.SysUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserRoleDTO extends SysUser {
    List<SysRole> sysRoles;
}
