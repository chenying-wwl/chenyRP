package com.java2023.exam12.bproject.dto;

import com.java2023.exam12.bproject.entity.SysPermission;
import lombok.Getter;
import lombok.Setter;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
@Setter
@Getter
public class PermissionDTO extends SysPermission {
    //存储子节点
    List<PermissionDTO> children = new ArrayList<PermissionDTO>();
}
