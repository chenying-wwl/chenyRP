package com.java2023.exam12.bproject.controller;

import com.java2023.exam12.bproject.entity.TbMaterials;
import com.java2023.exam12.bproject.service.Impl.MaterialService;
import com.java2023.exam12.bproject.vo.MaterialVo;
import com.java2023.exam12.bproject.vo.ResultVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tbMaterial")
@Api("材料管理")
public class TbMaterialController {
    @Autowired
    MaterialService materialService;
    @GetMapping("getAll")
    public ResultVo getAll(){
        List<TbMaterials> lsit = materialService.queryAll();
        return ResultVo.ok(null,lsit);
    }
    @PostMapping
    public ResultVo add(MaterialVo materialVo){
        materialService.add(materialVo);
        return ResultVo.ok("添加材料成功",null);
    }
}
