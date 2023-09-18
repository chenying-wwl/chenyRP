package com.java2023.exam12.bproject.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.io.Serializable;

/**
 * 材料类型(TbMaterialType)实体类
 *
 * @author makejava
 * @since 2023-08-18 19:53:59
 */
@Table("tb_material_type")
public class TbMaterialType implements Serializable {
    private static final long serialVersionUID = -85936063709783211L;
    /**
     * 材料ID
     */
    @Id(keyType = KeyType.Auto)
    private Integer typeId;
    /**
     * 材料类型编号
     */
    private String typeCode;
    /**
     * 材料类型名称
     */
    private String typeName;
    /**
     * 材料类型描述
     */
    private String typeDescrible;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDescrible() {
        return typeDescrible;
    }

    public void setTypeDescrible(String typeDescrible) {
        this.typeDescrible = typeDescrible;
    }

}

