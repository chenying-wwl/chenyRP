package com.java2023.exam12.bproject.entity;

import java.io.Serializable;

/**
 * 产品类型表(TbProductType)实体类
 *
 * @author makejava
 * @since 2023-08-18 19:53:59
 */
public class TbProductType implements Serializable {
    private static final long serialVersionUID = 838315708673261629L;
    /**
     * 标识ID
     */
    private Integer typeId;
    /**
     * 类型编号
     */
    private String typeCode;
    /**
     * 类型名称
     */
    private String typeName;
    /**
     * 类型描述
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

