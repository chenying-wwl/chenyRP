package com.java2023.exam12.bproject.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WareMaterialVo {
    private Integer id;
    /**
     * 货仓材料ID
     */
    private String wareMaterialName;
    /**
     * 所属货仓ID
     */
    private String wareHouseName;
    /**
     * 货仓材料量
     */
    private Integer wareMaterialNum;
    /**
     * 存放位置
     */
    private String wareMaterialLocation;
    private String spaceName;
}
