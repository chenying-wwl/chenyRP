package com.java2023.exam12.bproject.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.io.Serializable;

/**
 * 仓储材料(TbMaterials)实体类
 *
 * @author makejava
 * @since 2023-08-18 19:53:59
 */
@Table("tb_materials")
public class TbMaterials implements Serializable {
    private static final long serialVersionUID = 483844334880033689L;
    /**
     * 材料标识ID
     */
    @Id(keyType = KeyType.Auto)
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
    @Column(isLogicDelete = true)
    private Integer isDelete=0;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMCode() {
        return mCode;
    }

    public void setMCode(String mCode) {
        this.mCode = mCode;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getMTypeId() {
        return mTypeId;
    }

    public void setMTypeId(String mTypeId) {
        this.mTypeId = mTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}

