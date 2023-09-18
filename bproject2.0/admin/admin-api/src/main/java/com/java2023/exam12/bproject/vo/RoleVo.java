package com.java2023.exam12.bproject.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
@Setter
@Getter
public class RoleVo {
    Integer rid;
    List<Integer> perms;
}
