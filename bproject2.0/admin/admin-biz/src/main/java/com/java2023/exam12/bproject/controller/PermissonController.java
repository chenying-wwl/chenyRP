package com.java2023.exam12.bproject.controller;

import com.java2023.exam12.bproject.dto.PermissionDTO;
import com.java2023.exam12.bproject.entity.SysPermission;
import com.java2023.exam12.bproject.service.PermService;
import com.java2023.exam12.bproject.vo.ResultVo;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
@RestController
@RequestMapping("perm")
public class PermissonController {
    @Resource
    PermService permService;
    @PostMapping
    public ResultVo add(SysPermission permission){
        permService.add(permission);
        return ResultVo.ok("新增权限成功");
    }

    @GetMapping
    public ResultVo listAll(){
        List<PermissionDTO> list = permService.list();
        return ResultVo.ok("",list);
    }
    @GetMapping("query")
    public ResultVo query(Integer pageIndex, Integer pageSize){
        Map<String,Object> map = permService.query(pageIndex,pageSize);
        return ResultVo.ok("",map);
    }
    @GetMapping("{id}")
    public ResultVo findById(@PathVariable Integer id){
        return ResultVo.ok(null,permService.findById(id));
    }
    @DeleteMapping("{id}")
    public ResultVo deleteById(@PathVariable Integer id){
        permService.deleteById(id);
        return ResultVo.ok("删除结点成功");
    }
}
