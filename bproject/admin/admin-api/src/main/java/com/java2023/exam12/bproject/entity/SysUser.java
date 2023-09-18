package com.java2023.exam12.bproject.entity;

import java.io.Serializable;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.ToString;

/**
 * 用户表(SysUser)实体类
 *
 * @author makejava
 * @since 2023-08-14 17:01:14
 */
@Table("sys_user")
@ToString
public class SysUser implements Serializable {
    private static final long serialVersionUID = -11474909990817951L;
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
    Boolean isDelete;
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

}

