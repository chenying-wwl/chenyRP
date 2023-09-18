package com.java2023.exam12.bproject.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class StockOutInVo {

    /**
     * 出库还是入库
     */
    private Integer stoctIsOut;
    /**
     * 出库申请人
     */
    private String stockOutUser;

    private String stockGoodName;
    /**
     * 出库量
     */
    private Integer stockOutNum;

    private String approvalName;
    private String wareHouseName;
    private String wareSpace;
}
