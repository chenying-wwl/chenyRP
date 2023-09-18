package com.java2023.exam12.bproject.service;

import com.java2023.exam12.bproject.entity.TbMaterials;
import com.java2023.exam12.bproject.entity.TbWareMaterial;
import com.java2023.exam12.bproject.entity.TbWarehouse;
import com.java2023.exam12.bproject.vo.WareMaterialVo;

import java.util.Map;

/**
 * 仓库材料表(TbWareMaterial)表服务接口
 *
 * @author makejava
 * @since 2023-08-18 20:18:29
 */
public interface TbWareMaterialService {

    public void add(WareMaterialVo wareMaterialVo);

    public Map<String,Object> query(String materialName,String warehouseName ,Integer pageIndex, Integer pageSize);

    public void deleteById(Integer id);

    public void edit(WareMaterialVo wareMaterialVo);
    public TbWareMaterial getWareMaterialById(Integer id);

    public TbMaterials getByMaterialName(String materialName);
    public TbWarehouse getByWarehouseName(String warehouseName);
//
//    public void grantPerm(RoleVo roleVo);
//
//    RoleVo getRole(Integer rid);

}
