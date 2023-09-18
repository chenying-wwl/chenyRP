package com.java2023.exam12.bproject.entity;

import java.io.Serializable;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
/**
 * 角色表(SysRole)实体类
 *
 * @author makejava
 * @since 2023-08-14 16:59:29
 */
@Table("sys_role")
public class SysRole implements Serializable {
    private static final long serialVersionUID = -59983420610172776L;
/**
     * 角色编号
     */
    @Id(keyType = KeyType.Auto)
    private Integer rid;
/**
     * 角色名称
     */

    private String rname;
    @Column(isLogicDelete = true)
    private Integer isDelete=0;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

}

