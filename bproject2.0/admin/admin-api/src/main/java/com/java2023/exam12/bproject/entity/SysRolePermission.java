package com.java2023.exam12.bproject.entity;

import java.io.Serializable;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
/**
 * 角色权限表(SysRolePermission)实体类
 *
 * @author makejava
 * @since 2023-08-14 17:01:14
 */
@Table("sys_role_permission")
public class SysRolePermission implements Serializable {
    private static final long serialVersionUID = 228815913116823082L;
/**
     * 角色id
     */
    private Integer roleId;
/**
     * 权限id
     */
    private Integer permissionId;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

}

