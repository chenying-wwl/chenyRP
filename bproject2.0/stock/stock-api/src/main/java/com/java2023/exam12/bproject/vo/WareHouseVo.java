package com.java2023.exam12.bproject.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WareHouseVo {
    private Integer wareId;
    /**
     * 仓库编号
     */
    private String wareCode;
    /**
     * 仓库名称
     */
    private String wareName;
    /**
     * 仓库地址
     */
    private String wareAddress;
    /**
     * 联系人电话
     */
    private String personPhone;
}
