package com.java2023.exam12.bproject.service;


import com.java2023.exam12.bproject.entity.SysUser;
import com.java2023.exam12.bproject.vo.UserVo;
import io.swagger.models.auth.In;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {
    public void addUser(UserVo userVo);

    public List<SysUser> query();

    public Map<String, Object> queryUserAndRole(String username, Integer page,Integer size);

    public void deleteUser(Integer uid);

    public void editUser(UserVo userVo);
}
