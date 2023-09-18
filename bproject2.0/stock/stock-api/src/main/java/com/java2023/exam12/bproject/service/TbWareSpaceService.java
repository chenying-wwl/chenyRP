package com.java2023.exam12.bproject.service;

import com.java2023.exam12.bproject.entity.TbMaterials;
import com.java2023.exam12.bproject.entity.TbWareSpace;
import com.java2023.exam12.bproject.entity.TbWarehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 仓库仓位(TbWareSpace)表服务接口
 *
 * @author makejava
 * @since 2023-08-19 16:15:19
 */
public interface TbWareSpaceService {

    /**
     * 通过ID查询单条数据
     *
     * @param spaceId 主键
     * @return 实例对象
     */
    TbWareSpace queryById(Integer spaceId);

    List<TbWareSpace> getAll();
    List<TbWareSpace> getByWareHouse(String warehouseName);
}
