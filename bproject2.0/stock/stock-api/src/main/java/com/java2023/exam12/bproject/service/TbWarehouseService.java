package com.java2023.exam12.bproject.service;

import com.java2023.exam12.bproject.entity.TbWarehouse;
import com.java2023.exam12.bproject.vo.WareHouseVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 货仓表(TbWarehouse)表服务接口
 *
 * @author makejava
 * @since 2023-08-19 13:57:22
 */
public interface TbWarehouseService {

    /**
     * 通过ID查询单条数据
     *
     * @param wareId 主键
     * @return 实例对象
     */
    TbWarehouse queryById(Integer wareId);
    //通过货仓名称查找
    TbWarehouse queryByName(String warehouseName);

    //获取所有的货仓
    List<TbWarehouse> queryAll();

    void add(WareHouseVo wareHouseVo);



    /**
     * 通过主键删除数据
     *
     * @param wareId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer wareId);

}
