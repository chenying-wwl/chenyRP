package com.java2023.exam12.bproject.service.Impl;

import com.java2023.exam12.bproject.dao.*;
import com.java2023.exam12.bproject.entity.*;
import com.java2023.exam12.bproject.service.TbApprovalFlowService;
import com.java2023.exam12.bproject.vo.StockOutInVo;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.java2023.exam12.bproject.entity.table.TbApprovalFlowTableDef.TB_APPROVAL_FLOW;
import static com.java2023.exam12.bproject.entity.table.TbStockOutInTableDef.TB_STOCK_OUT_IN;
import static com.java2023.exam12.bproject.entity.table.TbWareMaterialTableDef.TB_WARE_MATERIAL;
import static com.java2023.exam12.bproject.entity.table.TbWareSpaceTableDef.TB_WARE_SPACE;
import static com.java2023.exam12.bproject.entity.table.TbWarehouseTableDef.TB_WAREHOUSE;

@Service
public class approvalService implements TbApprovalFlowService {
    @Resource
    TbApprovalDao tbApprovalDao;
    @Resource
    TbStockOutInDao stockOutInDao;
    @Resource
    TbWareMaterialDao tbWareMaterialDao;
    @Resource
    TbWareSpaceDao tbWareSpaceDao;
    @Resource
    TbWarehouseDao tbWarehouseDao;
    @Resource
    TbRecordsDao tbRecordsDao;
    @Override
    public Map<String, Object> query(String username,Integer pageIndex, Integer pageSize) {
        QueryWrapper query=new QueryWrapper()
                .where(TB_APPROVAL_FLOW.APPROVAL_USER.eq(username));

        Page<TbApprovalFlow> page = tbApprovalDao.paginate(pageIndex,pageSize,query);
        Map<String,Object> map = new HashMap<>();
        //每页的数据
        List<TbApprovalFlow> list = page.getRecords();
        map.put("list",list);
        //获取总数
        Long total = page.getTotalRow();
        map.put("total",total);
        return map;
    }

    @Override
    public void approval(Integer id,Integer isApproval) {
        TbApprovalFlow approvalFlow = tbApprovalDao.selectOneById(id);
        TbStockOutIn tbStockOutIn = stockOutInDao.selectOneById(approvalFlow.getStockRequestId());
        approvalFlow.setIsApproval(isApproval);
        approvalFlow.setApprovalTime(new Date());
        tbStockOutIn.setStatus(isApproval);
        stockOutInDao.update(tbStockOutIn);
        tbApprovalDao.update(approvalFlow);
        if(isApproval==1){
            TbWareMaterial tbWareMaterial = tbWareMaterialDao.selectOneByQuery(new QueryWrapper()
                    .where(TB_WARE_MATERIAL.WARE_HOUSE_NAME.eq(tbStockOutIn.getStockGoodName())
                            .and(TB_WARE_MATERIAL.WARE_MATERIAL_NAME.eq(tbStockOutIn.getWareHouseName())
                                    .and(TB_WARE_MATERIAL.SPACE_NAME.eq(tbStockOutIn.getWareSpace())))));
            TbWarehouse tbWarehouse = tbWarehouseDao.selectOneByQuery(new QueryWrapper()
                    .where(TB_WAREHOUSE.WARE_NAME.eq(tbStockOutIn.getWareHouseName())));
            TbWareSpace tbWareSpace = tbWareSpaceDao.selectOneByQuery(new QueryWrapper()
                    .where(TB_WARE_SPACE.WARE_ID.eq(tbWarehouse.getWareId())
                            .and(TB_WARE_SPACE.SPACE_NAME.eq(tbStockOutIn.getWareSpace()))));
            //根据
            if(tbStockOutIn.getStatus()==0){
                //表明是出库操作
                //拿到出库得仓位，根据仓位，材料

                tbWareMaterial.setWareMaterialNum(tbWareMaterial.getWareMaterialNum()-tbStockOutIn.getStockOutNum());
                tbWareSpace.setBlockCapacity(tbWareSpace.getBlockCapacity()+tbStockOutIn.getStockOutNum());


            }else {
                //表明是入库操作
                tbWareMaterial.setWareMaterialNum(tbWareMaterial.getWareMaterialNum()+tbStockOutIn.getStockOutNum());
                tbWareSpace.setBlockCapacity(tbWareSpace.getBlockCapacity()-tbStockOutIn.getStockOutNum());
            }
            //批准了申请，出入库情况记录到出入库记录表表
            TbRecords tbRecords = new TbRecords();
            tbRecords.setStockGood(tbStockOutIn.getStockGoodName());
            tbRecords.setStockUser(tbStockOutIn.getStockOutUser());
            tbRecords.setStockApproval(tbStockOutIn.getApprovalName());
            tbRecords.setStockRequestTime(tbStockOutIn.getStockOutDate());
            tbRecords.setStockApprovalTime(new Date());
            tbRecords.setStockInOut(tbStockOutIn.getStoctIsOut());
            tbRecords.setStockNum(tbStockOutIn.getStockOutNum());
            tbRecordsDao.insert(tbRecords);
        }

    }

    @Override
    public void deletetbApproval(Integer id) {
        tbApprovalDao.deleteById(id);
    }
}
