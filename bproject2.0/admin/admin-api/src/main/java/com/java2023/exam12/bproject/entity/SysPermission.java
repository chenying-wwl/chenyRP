package com.java2023.exam12.bproject.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.io.Serializable;

/**
 * 权限表(SysPermission)实体类
 *
 * @author makejava
 * @since 2023-08-14 16:52:40
 */
@Table("sys_permission")
public class SysPermission implements Serializable {
    private static final long serialVersionUID = -59801468339176620L;
/**
     * 权限id
     */
    @Id(keyType = KeyType.Auto)
    private Integer permId;
/**
     * 权限名称
     */
    private String permName;
/**
     * 权限路径
     */
    private String permUrl;
/**
     * 权限类型
     */
    private String permType;
/**
     * 父类id
     */
    private Integer parentId;
/**
     * 权限编码
     */
    private String permCode;


    public Integer getPermId() {
        return permId;
    }

    public void setPermId(Integer permId) {
        this.permId = permId;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public String getPermUrl() {
        return permUrl;
    }

    public void setPermUrl(String permUrl) {
        this.permUrl = permUrl;
    }

    public String getPermType() {
        return permType;
    }

    public void setPermType(String permType) {
        this.permType = permType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPermCode() {
        return permCode;
    }

    public void setPermCode(String permCode) {
        this.permCode = permCode;
    }

}

