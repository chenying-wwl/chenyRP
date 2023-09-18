package com.java2023.exam12.bproject.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.io.Serializable;

/**
 * 仓库材料表(TbWareMaterial)实体类
 *
 * @author makejava
 * @since 2023-08-19 11:14:29
 */
@Table("tb_ware_material")
public class TbWareMaterial implements Serializable {
    private static final long serialVersionUID = 638112782228850807L;
    /**
     * 标识ID
     */
    @Id(keyType = KeyType.Auto)
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
    @Column("ware_space_name")
    private String spaceName;
    private String unit;
    private  String materialCode;
    @Column(isLogicDelete = true)
    private Integer isDelete=0;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWareMaterialName() {
        return wareMaterialName;
    }

    public void setWareMaterialName(String wareMaterialName) {
        this.wareMaterialName = wareMaterialName;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    public Integer getWareMaterialNum() {
        return wareMaterialNum;
    }

    public void setWareMaterialNum(Integer wareMaterialNum) {
        this.wareMaterialNum = wareMaterialNum;
    }

    public String getWareMaterialLocation() {
        return wareMaterialLocation;
    }

    public void setWareMaterialLocation(String wareMaterialLocation) {
        this.wareMaterialLocation = wareMaterialLocation;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }
}

