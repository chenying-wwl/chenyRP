package com.java2023.exam12.bproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.util.Date;
import java.io.Serializable;

/**
 * 审批流程表(TbApprovalFlow)实体类
 *
 * @author makejava
 * @since 2023-08-18 19:53:59
 */
@Table("tb_approval_flow")
public class TbApprovalFlow implements Serializable {
    private static final long serialVersionUID = 486326386667036423L;
    /**
     * 审批ID
     */
    @Id(keyType = KeyType.Auto)
    private Integer approvalId;
    /**
     * 审批人
     */
    private String approvalUser;
    /**
     * 是否审批
     */
    private Integer isApproval=0;//0代表未审核，1代表批准，2代表不批准
    /**
     * 出入库申请表ID
     */
    private Integer stockRequestId;
    /**
     * 审批时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date approvalTime;


    public Integer getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Integer approvalId) {
        this.approvalId = approvalId;
    }

    public String getApprovalUser() {
        return approvalUser;
    }

    public void setApprovalUser(String approvalUser) {
        this.approvalUser = approvalUser;
    }

    public Integer getIsApproval() {
        return isApproval;
    }

    public void setIsApproval(Integer isApproval) {
        this.isApproval = isApproval;
    }

    public Integer getStockRequestId() {
        return stockRequestId;
    }

    public void setStockRequestId(Integer stockRequestId) {
        this.stockRequestId = stockRequestId;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

}

