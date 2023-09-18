package com.java2023.exam12.bproject.entity;

import java.io.Serializable;

/**
 * 材料产品表(TbProduct)实体类
 *
 * @author makejava
 * @since 2023-08-18 19:53:59
 */
public class TbProduct implements Serializable {
    private static final long serialVersionUID = 672641223158370197L;
    /**
     * 产品标识ID
     */
    private Integer productId;
    /**
     * 产品编号
     */
    private String productCode;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品类型ID
     */
    private Integer productTypeId;
    /**
     * 产品质量ID
     */
    private Integer productQuatityId;
    /**
     * 产品单位
     */
    private String productUnit;
    /**
     * 产品描述
     */
    private String productDescrible;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getProductQuatityId() {
        return productQuatityId;
    }

    public void setProductQuatityId(Integer productQuatityId) {
        this.productQuatityId = productQuatityId;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getProductDescrible() {
        return productDescrible;
    }

    public void setProductDescrible(String productDescrible) {
        this.productDescrible = productDescrible;
    }

}

