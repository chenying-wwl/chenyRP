package com.java2023.exam12.bproject.controller;

import com.java2023.exam12.bproject.entity.TbWarehouse;
import com.java2023.exam12.bproject.service.Impl.WareHouseService;
import com.java2023.exam12.bproject.vo.MaterialVo;
import com.java2023.exam12.bproject.vo.ResultVo;
import com.java2023.exam12.bproject.vo.WareHouseVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tbWareHouse")
@Api("货仓管理")
public class TbWareHouseController {
    @Autowired
    WareHouseService wareHouseService;

    @GetMapping("getAll")
    public ResultVo getAll(){
        List<TbWarehouse> list =wareHouseService.queryAll();
        return ResultVo.ok(null,list);
    }
    @PostMapping
    public ResultVo add(WareHouseVo wareHouseVo){
        wareHouseService.add(wareHouseVo);
        return ResultVo.ok("添加仓库成功",null);
    }
}
