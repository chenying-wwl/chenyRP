package com.java2023.exam12.bproject.service.Impl;

import com.java2023.exam12.bproject.dao.TbMaterialTypeDao;
import com.java2023.exam12.bproject.dao.TbWarehouseDao;
import com.java2023.exam12.bproject.entity.TbWarehouse;
import com.java2023.exam12.bproject.service.TbWarehouseService;
import com.java2023.exam12.bproject.vo.WareHouseVo;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.java2023.exam12.bproject.entity.table.TbMaterialsTableDef.TB_MATERIALS;
import static com.java2023.exam12.bproject.entity.table.TbWarehouseTableDef.TB_WAREHOUSE;

@Service
public class WareHouseService implements TbWarehouseService {
    @Resource
    TbWarehouseDao tbWarehouseDao;

    @Override
    public TbWarehouse queryById(Integer wareId) {
        return null;
    }

    @Override
    public TbWarehouse queryByName(String warehouseName) {
        return null;
    }

    @Override
    public List<TbWarehouse> queryAll() {
        List<TbWarehouse> list = tbWarehouseDao.selectAll();
        return list;
    }

    @Override

    public void add(WareHouseVo wareHouseVo) {
        //判断是否已经有该仓库名称了
        TbWarehouse tbWarehouse1 = tbWarehouseDao.selectOneByQuery(new QueryWrapper()
                .where(TB_WAREHOUSE.WARE_NAME.eq(wareHouseVo.getWareName())));
        if(tbWarehouse1!=null){
            throw new RuntimeException("该仓库已经存在！");
        }
        TbWarehouse tbWarehouse = new TbWarehouse();

        tbWarehouse.setWareCode(wareHouseVo.getWareCode());
        tbWarehouse.setWareName(wareHouseVo.getWareName());
        tbWarehouse.setWareAddress(wareHouseVo.getWareAddress());
        tbWarehouse.setPersonPhone(wareHouseVo.getPersonPhone());
        tbWarehouseDao.insert(tbWarehouse);
    }

    @Override
    public boolean deleteById(Integer wareId) {
        return false;
    }
}
