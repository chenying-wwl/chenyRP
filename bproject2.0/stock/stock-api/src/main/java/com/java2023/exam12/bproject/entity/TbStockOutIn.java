package com.java2023.exam12.bproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.util.Date;
import java.io.Serializable;

/**
 * 出入库申请表(TbStockOutIn)实体类
 *
 * @author makejava
 * @since 2023-08-22 22:17:15
 */
@Table("tb_stock_out_in")
public class TbStockOutIn implements Serializable {
    private static final long serialVersionUID = 173522012788583291L;
    /**
     * 出库ID
     */
    @Id(keyType = KeyType.Auto)
    private Integer stockOutId;
    /**
     * 申请时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date stockOutDate;
    /**
     * 出库还是入库
     */
    private Integer stoctIsOut;//0表示出库，1表示入库
    /**
     * 出库申请人
     */
    private String stockOutUser;
    /**
     * 是否被批准
     */
    private Integer status=0;//0代表未审核，1代表批准，2代表不批准
    private String stockGoodName;
    private String wareSpace;
    /**
     * 出库量
     */
    private Integer stockOutNum;

    private String approvalName;

    private String wareHouseName;
    public Integer getStockOutId() {
        return stockOutId;
    }

    public void setStockOutId(Integer stockOutId) {
        this.stockOutId = stockOutId;
    }

    public Date getStockOutDate() {
        return stockOutDate;
    }

    public void setStockOutDate(Date stockOutDate) {
        this.stockOutDate = stockOutDate;
    }

    public Integer getStoctIsOut() {
        return stoctIsOut;
    }

    public void setStoctIsOut(Integer stoctIsOut) {
        this.stoctIsOut = stoctIsOut;
    }

    public String getStockOutUser() {
        return stockOutUser;
    }

    public void setStockOutUser(String stockOutUser) {
        this.stockOutUser = stockOutUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStockGoodName() {
        return stockGoodName;
    }

    public void setStockGoodName(String stockGoodName) {
        this.stockGoodName = stockGoodName;
    }

    public Integer getStockOutNum() {
        return stockOutNum;
    }

    public void setStockOutNum(Integer stockOutNum) {
        this.stockOutNum = stockOutNum;
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    public String getWareSpace() {
        return wareSpace;
    }

    public void setWareSpace(String wareSpace) {
        this.wareSpace = wareSpace;
    }
}

