package com.java2023.exam12.bproject.dao;

import com.java2023.exam12.bproject.dto.WareMaterialDTO;
import com.java2023.exam12.bproject.entity.TbWareMaterial;
import com.mybatisflex.core.BaseMapper;

import java.util.List;

/**
 * 仓库材料表(TbWareMaterial)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-19 08:37:00
 */
public interface TbWareMaterialDao extends BaseMapper<TbWareMaterial> {

    List<WareMaterialDTO> query(String materialName , String warehouseName, Integer pageIndex, Integer pageSize);
}

