package com.java2023.exam12.bproject.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.util.Date;
import java.io.Serializable;

/**
 * 出入库记录表(TbRecords)实体类
 *
 * @author makejava
 * @since 2023-08-24 19:48:35
 */
@Table("tb_records")
public class TbRecords implements Serializable {
    private static final long serialVersionUID = -56550108246788808L;
    /**
     * 记录表ID
     */
    @Id(keyType = KeyType.Auto)
    private Integer id;
    /**
     * 出入库货物
     */
    private String stockGood;
    /**
     * 申请时间
     */
    private Date stockRequestTime;
    /**
     * 批准时间
     */
    private Date stockApprovalTime;
    /**
     * 申请人
     */
    private String stockUser;
    /**
     * 批准人
     */
    private String stockApproval;
    /**
     * 出库入库
     */
    private Integer stockInOut;
    /**
     * 出入库的位置
     */
    private String stockLocation;
    /**
     * 出入库量
     */
    private Integer stockNum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStockGood() {
        return stockGood;
    }

    public void setStockGood(String stockGood) {
        this.stockGood = stockGood;
    }

    public Date getStockRequestTime() {
        return stockRequestTime;
    }

    public void setStockRequestTime(Date stockRequestTime) {
        this.stockRequestTime = stockRequestTime;
    }

    public Date getStockApprovalTime() {
        return stockApprovalTime;
    }

    public void setStockApprovalTime(Date stockApprovalTime) {
        this.stockApprovalTime = stockApprovalTime;
    }

    public String getStockUser() {
        return stockUser;
    }

    public void setStockUser(String stockUser) {
        this.stockUser = stockUser;
    }

    public String getStockApproval() {
        return stockApproval;
    }

    public void setStockApproval(String stockApproval) {
        this.stockApproval = stockApproval;
    }

    public Integer getStockInOut() {
        return stockInOut;
    }

    public void setStockInOut(Integer stockInOut) {
        this.stockInOut = stockInOut;
    }

    public String getStockLocation() {
        return stockLocation;
    }

    public void setStockLocation(String stockLocation) {
        this.stockLocation = stockLocation;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

}

