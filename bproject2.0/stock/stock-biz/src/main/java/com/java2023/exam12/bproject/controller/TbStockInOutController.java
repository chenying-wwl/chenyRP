package com.java2023.exam12.bproject.controller;

import com.java2023.exam12.bproject.entity.TbStockOutIn;
import com.java2023.exam12.bproject.service.TbStockOutInService;
import com.java2023.exam12.bproject.vo.ResultVo;
import com.java2023.exam12.bproject.vo.StockOutInVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("tbStockInOUt")
@Api("出入库申请表")
public class TbStockInOutController {
    @Autowired
    TbStockOutInService stockOutInService;
    @PostMapping
    public ResultVo addRequire(StockOutInVo stockOutInVo){
        String result = stockOutInService.require(stockOutInVo);
        return ResultVo.ok(result,null);
    }
    @GetMapping
    public ResultVo queryByUser(String username,Integer pageIndex, Integer pageSize){
        Map map = stockOutInService.query(username,pageIndex,pageSize);
        return ResultVo.ok(null,map);
    }
    @DeleteMapping("{id}")
    public ResultVo deleteById(@PathVariable Integer id){
        stockOutInService.delete(id);
        return ResultVo.ok("删除成功",null);
    }
    @GetMapping("getById")
    public ResultVo getById(Integer id){
        TbStockOutIn tbStockOutIn = stockOutInService.getById(id);
        return ResultVo.ok(null,tbStockOutIn);
    }
}
