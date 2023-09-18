package com.java2023.exam12.bproject.service.Impl;

import com.java2023.exam12.bproject.dao.TbMaterialsDao;
import com.java2023.exam12.bproject.dao.TbWareMaterialDao;
import com.java2023.exam12.bproject.dao.TbWareSpaceDao;
import com.java2023.exam12.bproject.dao.TbWarehouseDao;
import com.java2023.exam12.bproject.entity.TbMaterials;
import com.java2023.exam12.bproject.entity.TbWareMaterial;
import com.java2023.exam12.bproject.entity.TbWareSpace;
import com.java2023.exam12.bproject.entity.TbWarehouse;
import com.java2023.exam12.bproject.service.TbWareMaterialService;
import com.java2023.exam12.bproject.vo.WareMaterialVo;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.java2023.exam12.bproject.entity.table.TbMaterialsTableDef.TB_MATERIALS;
import static com.java2023.exam12.bproject.entity.table.TbWareMaterialTableDef.TB_WARE_MATERIAL;
import static com.java2023.exam12.bproject.entity.table.TbWareSpaceTableDef.TB_WARE_SPACE;
import static com.java2023.exam12.bproject.entity.table.TbWarehouseTableDef.TB_WAREHOUSE;

//import static com.java2023.exam12.bproject.entity.table.TbMaterialsTableDef.TB_WARE_HOUSE;


@Service
public class WareMaterialService implements TbWareMaterialService {
    @Resource
    TbWareMaterialDao tbWareMaterialDao;
    @Resource
    TbMaterialsDao tbMaterialsDao;
    @Resource
    TbWarehouseDao tbWarehouseDao;
    @Resource
    TbWareSpaceDao tbWareSpaceDao;

    @Override
    public void add(WareMaterialVo wareMaterialVo) {

        TbMaterials tbMaterials = tbMaterialsDao.selectOneByQuery(new QueryWrapper()
                .where(TB_MATERIALS.M_NAME.eq(wareMaterialVo.getWareMaterialName())));

        TbWarehouse tbWarehouse = tbWarehouseDao.selectOneByQuery(new QueryWrapper()
                .where(TB_WAREHOUSE.WARE_NAME.eq(wareMaterialVo.getWareHouseName())));

        TbWareSpace tbWareSpace = tbWareSpaceDao.selectOneByQuery(new QueryWrapper()
                .where(TB_WARE_SPACE.WARE_ID.eq(tbWarehouse.getWareId())
                        .and(TB_WARE_SPACE.SPACE_NAME.eq(wareMaterialVo.getSpaceName()))));
        String materialCode = tbMaterials.getMCode();
        String materialUnit = tbMaterials.getUnit();

        //查看选择的仓库仓位是否已经有该材料
        TbWareMaterial tbWareMaterial1 = tbWareMaterialDao.selectOneByQuery(new QueryWrapper()
                .where(TB_WARE_MATERIAL.WARE_HOUSE_NAME.eq(wareMaterialVo.getWareHouseName())
                        .and(TB_WARE_MATERIAL.WARE_MATERIAL_NAME.eq(wareMaterialVo.getWareMaterialName())
                                .and(TB_WARE_MATERIAL.SPACE_NAME.eq(wareMaterialVo.getSpaceName())))));
        if(tbWareMaterial1!=null){
            System.out.println("dnjwd");
            //当前仓位已经有该材料了，将材料加入该仓位
            tbWareMaterial1.setWareMaterialNum(tbWareMaterial1.getWareMaterialNum()+ wareMaterialVo.getWareMaterialNum());

            tbWareMaterialDao.update(tbWareMaterial1);
        }else {
            TbWareMaterial tbWareMaterial = new TbWareMaterial();
            tbWareMaterial.setMaterialCode(materialCode);
            tbWareMaterial.setWareMaterialName(wareMaterialVo.getWareMaterialName());
            tbWareMaterial.setWareHouseName(wareMaterialVo.getWareHouseName());
//        tbWareMaterial.setWareMaterialLocation(tbWareMaterial.getWareMaterialLocation());
            tbWareMaterial.setWareMaterialNum(wareMaterialVo.getWareMaterialNum());
            tbWareMaterial.setUnit(materialUnit);
            tbWareMaterial.setSpaceName(wareMaterialVo.getSpaceName());
            tbWareSpace.setBlockCapacity(tbWareSpace.getBlockCapacity()-wareMaterialVo.getWareMaterialNum());
            //最好之后再加上仓位之类的
            tbWareMaterialDao.insert(tbWareMaterial);
        }

    }

    @Override
    public Map<String, Object> query(String materialName, String warehouseName, Integer pageIndex, Integer pageSize) {
        if(materialName==null){
            materialName="";
        }
        materialName = "%"+materialName+"%";
        if(warehouseName==null){
            warehouseName="";
        }
        warehouseName = "%"+warehouseName+"%";


        QueryWrapper query=new QueryWrapper()
                .where(TB_WARE_MATERIAL.WARE_MATERIAL_NAME.like(materialName)
                        .and(TB_WARE_MATERIAL.WARE_HOUSE_NAME.like(warehouseName)));
        Page<TbWareMaterial> page = tbWareMaterialDao.paginate(pageIndex,pageSize,query);
        Map<String,Object> map = new HashMap<>();
        //每页的数据
        List<TbWareMaterial> list = page.getRecords();
        map.put("list",list);
        //获取总数
        Long total = page.getTotalRow();
        map.put("total",total);
        return map;

    }

    @Override
    public void deleteById(Integer id) {
        tbWareMaterialDao.deleteById(id);
    }

    @Override
    public void edit(WareMaterialVo wareMaterialVo) {
        TbMaterials tbMaterials = tbMaterialsDao.selectOneByQuery(new QueryWrapper()
                .where(TB_MATERIALS.M_NAME.eq(wareMaterialVo.getWareMaterialName())));
        String materialCode = tbMaterials.getMCode();
        String materialUnit = tbMaterials.getUnit();
        TbWareMaterial tbWareMaterial = new TbWareMaterial();
        tbWareMaterial.setId(wareMaterialVo.getId());
        tbWareMaterial.setMaterialCode(materialCode);
        tbWareMaterial.setWareMaterialName(wareMaterialVo.getWareMaterialName());
        tbWareMaterial.setWareHouseName(wareMaterialVo.getWareHouseName());
        tbWareMaterial.setWareMaterialLocation(tbWareMaterial.getWareMaterialLocation());
        tbWareMaterial.setWareMaterialNum(wareMaterialVo.getWareMaterialNum());
        tbWareMaterial.setUnit(materialUnit);
        tbWareMaterial.setSpaceName(wareMaterialVo.getSpaceName());
        tbWareMaterialDao.update(tbWareMaterial);

    }

    @Override
    public TbWareMaterial getWareMaterialById(Integer id) {
        return null;
    }

    @Override
    public TbMaterials getByMaterialName(String materialName) {
        return null;
    }

    @Override
    public TbWarehouse getByWarehouseName(String warehouseName) {
        return null;
    }


}
