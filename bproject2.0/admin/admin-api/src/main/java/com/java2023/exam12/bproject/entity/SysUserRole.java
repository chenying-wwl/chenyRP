package com.java2023.exam12.bproject.entity;

import java.io.Serializable;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
/**
 * 用户角色表(SysUserRole)实体类
 *
 * @author makejava
 * @since 2023-08-14 17:01:14
 */
@Table("sys_user_role")
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 811406995979534777L;
/**
     * 用户id
     */
    private Integer uid;
    /**
     * 角色id
     */
    private Integer rid;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

}

