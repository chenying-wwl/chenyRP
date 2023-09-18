package com.java2023.exam12.bproject.service;

import com.java2023.exam12.bproject.entity.TbStockOutIn;
import com.java2023.exam12.bproject.vo.StockOutInVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * 出入库申请表(TbStockOutIn)表服务接口
 *
 * @author makejava
 * @since 2023-08-22 22:17:15
 */
public interface TbStockOutInService {




    String require(StockOutInVo stockOutInVo);

    Map<String,Object> query(String username,Integer pageIndex, Integer pageSize);

    void delete(Integer id);

    TbStockOutIn getById(Integer id);
}
