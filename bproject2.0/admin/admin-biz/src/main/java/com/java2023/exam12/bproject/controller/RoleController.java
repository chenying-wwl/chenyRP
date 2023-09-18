package com.java2023.exam12.bproject.controller;

import com.java2023.exam12.bproject.entity.SysRole;
import com.java2023.exam12.bproject.entity.SysUser;
import com.java2023.exam12.bproject.service.RoleService;
import com.java2023.exam12.bproject.vo.ResultVo;
import com.java2023.exam12.bproject.vo.RoleVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
@RestController
@RequestMapping("role")
public class RoleController {
    @Resource
    RoleService roleService;
    @PostMapping
    public ResultVo add(SysRole role){
        roleService.add(role);
        return ResultVo.ok("插入角色成功",null);
    };
    @DeleteMapping("{id}")
    public ResultVo deleteById(@PathVariable Integer id){
        roleService.deleteById(id);
        return ResultVo.ok("角色删除成功",null);
    }
    @GetMapping("query")
    public ResultVo queryRole(String rname, Integer pageIndex, Integer pageSize){
        Map<String,Object> map = new HashMap<String,Object>();
        map= roleService.query(rname,pageIndex,pageSize);
        return ResultVo.ok("",map);
    }

    //角色授权
    @PostMapping("grantPerm")
    public ResultVo grantPerm(@RequestBody RoleVo roleVo){
        roleService.grantPerm(roleVo);
        return ResultVo.ok("授权成功");
    }

    @GetMapping("{rid}")
    public ResultVo get(@PathVariable Integer rid){
        return ResultVo.ok(null,roleService.getRole(rid));
    }
}
