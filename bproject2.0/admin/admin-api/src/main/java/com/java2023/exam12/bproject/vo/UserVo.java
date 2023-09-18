package com.java2023.exam12.bproject.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */

@Setter
@Getter
@ToString
public class UserVo {
    Integer userId;
    @NotEmpty(message = "用户名称不可以为空")
    String username;
    @NotEmpty(message = "用户密码不可以为空")
    String password;

    String nickname;

    List<String> permissions;
    List<Integer> roles;

}
