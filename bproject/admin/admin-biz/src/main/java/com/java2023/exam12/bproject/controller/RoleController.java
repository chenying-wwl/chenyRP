package com.java2023.exam12.bproject.controller;

import com.java2023.exam12.bproject.dao.SysRoleDao;
import com.java2023.exam12.bproject.entity.SysRole;
import com.java2023.exam12.bproject.service.RoleService;
import com.java2023.exam12.bproject.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @GetMapping
    public ResultVo getAllRole(){
        return ResultVo.ok(null,roleService.getAllRole());
    }
    @GetMapping("query")
    public ResultVo query(String username, Integer pageIndex, Integer pageSize){
        Map<String,Object> map = new HashMap<String,Object>();
        map= roleService.query(username,pageIndex,pageSize);
        return ResultVo.ok("",map);
    }
}
