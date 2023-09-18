package com.java2023.exam12.bproject.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MaterialVo {
    private Integer mid;
    /**
     * 材料编号
     */
    private String mCode;
    /**
     * 材料名称
     */
    private String mName;
    /**
     * 材料类型
     */
    private String mTypeId;
    /**
     * 材料描述
     */
    private String description;
    /**
     * 材料单位
     */
    private String unit;
}
