package com.java2023.exam12.bproject.service;

import com.java2023.exam12.bproject.entity.TbMaterials;
import com.java2023.exam12.bproject.entity.TbWarehouse;
import com.java2023.exam12.bproject.vo.MaterialVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 仓储材料(TbMaterials)表服务接口
 *
 * @author makejava
 * @since 2023-08-19 13:57:40
 */
public interface TbMaterialsService {

    /**
     * 通过ID查询单条数据
     *
     * @param mid 主键
     * @return 实例对象
     */
    TbMaterials queryById(Integer mid);

    TbMaterials queryByName(String warehouseName);

    //获取所有的货仓,不分页
    List<TbMaterials> queryAll();

    void add(MaterialVo materialVo);



    /**
     * 通过主键删除数据
     *
     * @param mid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer mid);

}
