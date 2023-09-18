package com.java2023.exam12.bproject.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.io.Serializable;

/**
 * 仓库仓位(TbWareSpace)实体类
 *
 * @author makejava
 * @since 2023-08-19 16:15:19
 */
@Table("tb_ware_space")
public class TbWareSpace implements Serializable {
    private static final long serialVersionUID = 255351033916179113L;
    /**
     * 仓位ID
     */
    @Id(keyType = KeyType.Auto)
    private Integer spaceId;
    /**
     * 仓位编号
     */
    private String spaceCode;
    /**
     * 仓位名称
     */
    private String spaceName;
    /**
     * 所属仓库ID
     */
    private Integer wareId;
    private Integer blockCapacity;

    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }

    public String getSpaceCode() {
        return spaceCode;
    }

    public void setSpaceCode(String spaceCode) {
        this.spaceCode = spaceCode;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public Integer getWareId() {
        return wareId;
    }

    public void setWareId(Integer wareId) {
        this.wareId = wareId;
    }

    public Integer getBlockCapacity() {
        return blockCapacity;
    }

    public void setBlockCapacity(Integer blockCapacity) {
        this.blockCapacity = blockCapacity;
    }
}

