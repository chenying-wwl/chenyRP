package com.java2023.exam12.bproject.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.io.Serializable;

/**
 * 货仓表(TbWarehouse)实体类
 *
 * @author makejava
 * @since 2023-08-18 19:53:59
 */
@Table("tb_warehouse")
public class TbWarehouse implements Serializable {
    private static final long serialVersionUID = -89263616075464142L;
    /**
     * 仓库ID
     */
    @Id(keyType = KeyType.Auto)
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
    @Column(isLogicDelete = true)
    private Integer isDelete=0;

    public Integer getWareId() {
        return wareId;
    }

    public void setWareId(Integer wareId) {
        this.wareId = wareId;
    }

    public String getWareCode() {
        return wareCode;
    }

    public void setWareCode(String wareCode) {
        this.wareCode = wareCode;
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName;
    }

    public String getWareAddress() {
        return wareAddress;
    }

    public void setWareAddress(String wareAddress) {
        this.wareAddress = wareAddress;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

}

