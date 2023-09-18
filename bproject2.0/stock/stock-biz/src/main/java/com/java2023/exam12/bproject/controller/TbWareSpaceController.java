package com.java2023.exam12.bproject.controller;

import com.java2023.exam12.bproject.service.TbWareSpaceService;
import com.java2023.exam12.bproject.vo.ResultVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tbWareSpace")
@Api("仓库仓位管理")
public class TbWareSpaceController {
    @Autowired
    TbWareSpaceService tbWareSpaceService;
    @GetMapping("getAll")
    public ResultVo getAll(){
        return ResultVo.ok(null,tbWareSpaceService.getAll());
    }
    @GetMapping("getByWareHouse")
    public ResultVo getByWareName(String warehouseName){
        List list = tbWareSpaceService.getByWareHouse(warehouseName);
        return ResultVo.ok(null,list);
    }
}
