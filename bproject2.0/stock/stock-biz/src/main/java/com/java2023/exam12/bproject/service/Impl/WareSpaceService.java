package com.java2023.exam12.bproject.service.Impl;

import com.java2023.exam12.bproject.dao.TbWareSpaceDao;
import com.java2023.exam12.bproject.dao.TbWarehouseDao;
import com.java2023.exam12.bproject.entity.TbWareSpace;
import com.java2023.exam12.bproject.entity.TbWarehouse;
import com.java2023.exam12.bproject.service.TbWareSpaceService;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.java2023.exam12.bproject.entity.table.TbWareSpaceTableDef.TB_WARE_SPACE;
import static com.java2023.exam12.bproject.entity.table.TbWarehouseTableDef.TB_WAREHOUSE;

@Service
public class WareSpaceService implements TbWareSpaceService {
    @Resource
    TbWareSpaceDao tbWareSpaceDao;
    @Resource
    TbWarehouseDao tbWarehouseDao;
    @Override
    public TbWareSpace queryById(Integer spaceId) {
        return null;
    }

    @Override
    public List<TbWareSpace> getAll() {
       return tbWareSpaceDao.selectAll();
    }

    @Override
    public List<TbWareSpace> getByWareHouse(String warehouseName) {
        TbWarehouse tbWarehouse = tbWarehouseDao.selectOneByQuery(new QueryWrapper()
                .where(TB_WAREHOUSE.WARE_NAME.eq(warehouseName)));
        Integer wareHouseId = tbWarehouse.getWareId();
        List<TbWareSpace> list = tbWareSpaceDao.selectListByQuery(new QueryWrapper()
                .where(TB_WARE_SPACE.WARE_ID.eq(wareHouseId)));
        return list;
    }
}
