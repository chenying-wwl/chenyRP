package com.java2023.exam12.bproject.controller;

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

//    @PreAuthorize("hasAuthority('user:delete')")
//    @DeleteMapping("{id}")
//    public ResultVo delete(@PathVariable int id){
//        return ResultVo.ok("删除用户成功",null);
//    }
    //用户的新增权限
//    @PreAuthorize("hasAuthority('user:add')")
//    @PostMapping
//    public ResultVo save(UserVo userVo){
//        return ResultVo.ok("新增用户成功",null);
//    }
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
    @GetMapping("query")
    public ResultVo query(String username, Integer pageIndex, Integer pageSize){
        Map<String,Object> map = new HashMap<String,Object>();
        map= userService.queryUserAndRole(username,pageIndex,pageSize);
        return ResultVo.ok("",map);
    }
    @PostMapping
    public ResultVo addUser(@RequestBody UserVo userVo){
        userService.addUser(userVo);
        return ResultVo.ok("添加成功",null);
    }
    @DeleteMapping("{uid}")
    public ResultVo deleteUser(@PathVariable Integer uid){
        userService.deleteUser(uid);
        return ResultVo.ok("删除成功"+uid,null);
    }
    @PutMapping
    public ResultVo editUser(@RequestBody UserVo userVo){
        userService.editUser(userVo);
        return ResultVo.ok("更新成功",null);
    }
}
