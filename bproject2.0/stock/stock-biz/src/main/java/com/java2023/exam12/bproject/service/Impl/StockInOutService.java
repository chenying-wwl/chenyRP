package com.java2023.exam12.bproject.service.Impl;

import com.java2023.exam12.bproject.dao.*;
import com.java2023.exam12.bproject.entity.TbApprovalFlow;
import com.java2023.exam12.bproject.entity.TbStockOutIn;
import com.java2023.exam12.bproject.entity.TbWareMaterial;
import com.java2023.exam12.bproject.entity.TbWareSpace;
import com.java2023.exam12.bproject.service.TbStockOutInService;
import com.java2023.exam12.bproject.vo.StockOutInVo;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.java2023.exam12.bproject.entity.table.TbStockOutInTableDef.TB_STOCK_OUT_IN;
import static com.java2023.exam12.bproject.entity.table.TbWareMaterialTableDef.TB_WARE_MATERIAL;
import static com.java2023.exam12.bproject.entity.table.TbWareSpaceTableDef.TB_WARE_SPACE;
import static com.java2023.exam12.bproject.entity.table.TbWarehouseTableDef.TB_WAREHOUSE;

@Service
public class StockInOutService implements TbStockOutInService {
    Integer total = 0;
    Integer spaceCompacity=0;
    @Resource
    TbStockOutInDao tbStockOutInDao;
    @Resource
    TbWareMaterialDao tbWareMaterialDao;
    @Resource
    TbApprovalDao tbapprovalDao;
    @Resource
    TbWareSpaceDao tbWareSpaceDao;
    @Resource
    TbWarehouseDao tbWarehouseDao;
    @Override
    public String require(StockOutInVo stockOutInVo) {
        //0表示出库
        if(stockOutInVo.getStoctIsOut()==0){
            //获取申请信息

            //判断仓库材料是否有库存

            List<TbWareMaterial> list = tbWareMaterialDao.selectListByQuery(new QueryWrapper()
                    .where(TB_WARE_MATERIAL.WARE_MATERIAL_NAME.eq(stockOutInVo.getStockGoodName())
                            .and(TB_WARE_MATERIAL.WARE_HOUSE_NAME.eq(stockOutInVo.getWareHouseName()))));
            list.forEach(tbWareMaterial ->
                    total+=tbWareMaterial.getWareMaterialNum()
            );
            //库存足够
            if(total>=stockOutInVo.getStockOutNum()){
                //生成申请表
                TbStockOutIn tbStockOutIn = new TbStockOutIn();
                tbStockOutIn.setStockGoodName(stockOutInVo.getStockGoodName());
                tbStockOutIn.setStockOutDate(new Date());
                tbStockOutIn.setStockOutNum(stockOutInVo.getStockOutNum());
                tbStockOutIn.setStockOutUser(stockOutInVo.getStockOutUser());
                tbStockOutIn.setStoctIsOut(stockOutInVo.getStoctIsOut());
                tbStockOutIn.setApprovalName(stockOutInVo.getApprovalName());
                tbStockOutIn.setWareHouseName(stockOutInVo.getWareHouseName());
                tbStockOutInDao.insert(tbStockOutIn);

                Integer requestId = tbStockOutIn.getStockOutId();
                //生成审核表
                TbApprovalFlow approvalFlow = new TbApprovalFlow();
                approvalFlow.setApprovalTime(new Date());
                approvalFlow.setApprovalUser(stockOutInVo.getApprovalName());
                approvalFlow.setStockRequestId(requestId);
                tbapprovalDao.insert(approvalFlow);
                //返回结果
                return "当前库存为"+total+",库存足够,申请提交成功，请等待审核";
            }else {
                //库存不够
                return "当前库存量为："+total+"吨"+",库存不足";
            }

        }
        //1表示入库
        else {
            //选择入库仓库，判断该仓库是否满足入库容量
            //便利所选仓库的所有仓位，获取可用容量
            Integer wareHouseId = tbWarehouseDao.selectOneByQuery(new QueryWrapper()
                    .where(TB_WAREHOUSE.WARE_NAME.eq(stockOutInVo.getWareHouseName()))).getWareId();
            List<TbWareSpace> spaceList = tbWareSpaceDao.selectListByQuery(new QueryWrapper()
                    .where(TB_WARE_SPACE.WARE_ID.eq(wareHouseId)));
            spaceList.forEach(tbWareSpace -> {
                spaceCompacity+=tbWareSpace.getBlockCapacity();
            });
            if(spaceCompacity>=stockOutInVo.getStockOutNum()){
                //生成申请表
                TbStockOutIn tbStockOutIn = new TbStockOutIn();
                tbStockOutIn.setStockGoodName(stockOutInVo.getStockGoodName());
                tbStockOutIn.setStockOutDate(new Date());
                tbStockOutIn.setStockOutNum(stockOutInVo.getStockOutNum());
                tbStockOutIn.setStockOutUser(stockOutInVo.getStockOutUser());
                tbStockOutIn.setStoctIsOut(stockOutInVo.getStoctIsOut());
                tbStockOutIn.setApprovalName(stockOutInVo.getApprovalName());
                tbStockOutIn.setWareHouseName(stockOutInVo.getWareHouseName());
                tbStockOutInDao.insert(tbStockOutIn);

                Integer requestId = tbStockOutIn.getStockOutId();
                //生成审核表
                TbApprovalFlow approvalFlow = new TbApprovalFlow();
                approvalFlow.setApprovalTime(new Date());
                approvalFlow.setApprovalUser(stockOutInVo.getApprovalName());
                approvalFlow.setStockRequestId(requestId);
                tbapprovalDao.insert(approvalFlow);
                //返回结果
                return "当前仓库可存"+spaceCompacity+"吨"+",可存储该批材料,申请提交成功，请等待审核";
            }else {
                return "当前仓库可存为："+spaceCompacity+"吨"+",暂时无法存储该批材料";
            }
        }

    }

    @Override
    public Map<String, Object> query(String username,Integer pageIndex, Integer pageSize) {
        QueryWrapper query=new QueryWrapper()
                .where(TB_STOCK_OUT_IN.STOCK_OUT_USER.eq(username));
        Page<TbStockOutIn> page = tbStockOutInDao.paginate(pageIndex,pageSize,query);
        Map<String,Object> map = new HashMap<>();
        //每页的数据
        List<TbStockOutIn> list = page.getRecords();
        map.put("list",list);
        //获取总数
        Long total = page.getTotalRow();
        map.put("total",total);
        return map;

    }

    @Override
    public void delete(Integer id) {
        tbStockOutInDao.deleteById(id);
    }

    @Override
    public TbStockOutIn getById(Integer id) {
        return tbStockOutInDao.selectOneById(id);

    }
}
