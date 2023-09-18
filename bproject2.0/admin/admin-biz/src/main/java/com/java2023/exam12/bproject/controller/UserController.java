package com.java2023.exam12.bproject.controller;

import com.java2023.exam12.bproject.dto.UserDTO;
import com.java2023.exam12.bproject.entity.SysRole;
import com.java2023.exam12.bproject.entity.SysUser;
import com.java2023.exam12.bproject.service.UserService;
import com.java2023.exam12.bproject.vo.ResultVo;
import com.java2023.exam12.bproject.vo.UserVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
@RestController
@RequestMapping("/user")
public class UserController {
    //用户的新增权限
    @GetMapping("info")
    public ResultVo info(){
        UserDetails loginUser = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        UserVo userVo = new UserVo();
        userVo.setUsername(loginUser.getUsername());
        userVo.setPermissions(new ArrayList<>());
        loginUser.getAuthorities().forEach(grantedAuthority -> {
            userVo.getPermissions().add(grantedAuthority.getAuthority());
        });
        return ResultVo.ok("",userVo);
    }

    @Resource
    UserService userService;
    @PostMapping("query")
    public ResultVo query(String username, Integer pageIndex, Integer pageSize){
        Map<String,Object> map = new HashMap<String,Object>();
        map= userService.queryUserAndRole(username,pageIndex,pageSize);
        return ResultVo.ok("",map);
    }


    @PostMapping
    public ResultVo add(@RequestBody UserVo userVo){
        userService.add(userVo);
        return ResultVo.ok("插入用户成功",null);
    };
    @DeleteMapping("{id}")
    public ResultVo deleteById(@PathVariable Integer id){
        userService.deleteById(id);
        return ResultVo.ok("用户删除成功",null);
    }

    @GetMapping("/find/{username}")
    public ResultVo getUserByName(@PathVariable String username){
        UserDTO user = userService.findByName(username);
        return ResultVo.ok(null,user);
    }

    @PutMapping()
    public ResultVo update(@RequestBody UserVo userVo){
        userService.update(userVo);
        return ResultVo.ok("更新用户成功",null);
    }
}
