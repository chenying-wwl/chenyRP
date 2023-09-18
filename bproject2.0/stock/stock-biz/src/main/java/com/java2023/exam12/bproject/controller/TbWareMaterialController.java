package com.java2023.exam12.bproject.controller;

import com.java2023.exam12.bproject.entity.TbWareMaterial;
import com.java2023.exam12.bproject.service.TbWareMaterialService;
import com.java2023.exam12.bproject.vo.ResultVo;
import com.java2023.exam12.bproject.vo.WareMaterialVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 仓库材料表(TbWareMaterial)表控制层
 *
 * @author makejava
 * @since 2023-08-19 08:56:46
 */
@RestController
@RequestMapping("tbWareMaterial")
@Api("货仓材料管理")
public class TbWareMaterialController {
    /**
     * 服务对象
     */
    @Resource
    private TbWareMaterialService tbWareMaterialService;


    @PostMapping
    public ResultVo insert(WareMaterialVo wareMaterialVo) {
        tbWareMaterialService.add(wareMaterialVo);
        return ResultVo.ok("加入仓库成功", null);
    }

    @GetMapping
    public ResultVo query(String materialName, String warehouseName, Integer pageIndex, Integer pageSize) {
        Map<String,Object> map = tbWareMaterialService.query(materialName,warehouseName,pageIndex,pageSize);
        return ResultVo.ok(null,map);
    }
    @DeleteMapping("{id}")
    public  ResultVo delete(@PathVariable Integer id){
        tbWareMaterialService.deleteById(id);
        return ResultVo.ok("删除仓库材料成功",null);
    }
    @PutMapping
    public  ResultVo edit(WareMaterialVo wareMaterialVo){
        tbWareMaterialService.edit(wareMaterialVo);
        return ResultVo.ok("修改成功",null);
    }

}
