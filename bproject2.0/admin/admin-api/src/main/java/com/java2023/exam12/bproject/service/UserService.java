package com.java2023.exam12.bproject.service;


import com.java2023.exam12.bproject.dto.UserDTO;
import com.java2023.exam12.bproject.entity.SysUser;
import com.java2023.exam12.bproject.vo.UserVo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {

    public Map<String, Object> queryUserAndRole(String username, Integer page, Integer size);

    void add(UserVo user);

    void deleteById(Integer id);

    UserDTO findByName(String name);

    void update(UserVo userVo);
}
