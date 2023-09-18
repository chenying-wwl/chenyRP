package com.java2023.exam12.bproject.entity;

import java.io.Serializable;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
/**
 * 用户表(SysUser)实体类
 *
 * @author makejava
 * @since 2023-08-16 14:56:34
 */
@Table("sys_user")
public class SysUser implements Serializable {
    private static final long serialVersionUID = -28137089602156698L;
/**
     * 用户的id
     */
    @Id(keyType = KeyType.Auto)
    private Integer userId;
/**
     * 用户账号
     */
    private String username;
/**
     * 用户密码
     */
    private String password;
/**
     * 用户昵称
     */
    private String nickname;

    @Column(isLogicDelete = true)
    private Integer isDelete=0;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

}

