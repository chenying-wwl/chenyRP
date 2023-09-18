package com.java2023.exam12.bproject.service.impl;

import com.java2023.exam12.bproject.dao.SysUserDao;
import com.java2023.exam12.bproject.dao.SysUserRoleDao;
import com.java2023.exam12.bproject.dto.UserRoleDTO;
import com.java2023.exam12.bproject.dto.UserDTO;
import com.java2023.exam12.bproject.entity.SysPermission;
import com.java2023.exam12.bproject.entity.SysUser;
import com.java2023.exam12.bproject.entity.SysUserRole;
import com.java2023.exam12.bproject.service.UserService;
import com.java2023.exam12.bproject.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Consumer;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    SysUserDao userDao;
    @Resource
    SysUserRoleDao sysUserRoleDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        UserDTO userDTO = userDao.findUserByNameWithPermissions(username);
        if(userDTO == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                //创建用户的权限
                List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
                //获取角色
                for (SysPermission permission :userDTO.getPermissions()) {
                    GrantedAuthority ag = new SimpleGrantedAuthority(permission.getPermCode());
                    list.add(ag);
                }
                return list;
            }

            @Override
            public String getPassword() {
                return userDTO.getPassword();
            }

            @Override
            public String getUsername() {
                return userDTO.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }


    @Override
    public void addUser(UserVo userVo) {
        SysUser user = new SysUser();

        user.setUsername(userVo.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userVo.getPassword()));
        user.setNickname(user.getNickname());
        userDao.insert(user);
        Integer uid = user.getUserId();
        List<SysUserRole> list = new ArrayList<>();
        if(userVo.getRoles()!=null){
            userVo.getRoles().forEach(integer -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUid(uid);
                sysUserRole.setRid(integer);
                list.add(sysUserRole);
            });
        }
        sysUserRoleDao.insertBatch(list);
    }

    @Override
    public List<SysUser> query() {
        return userDao.selectAll();
    }

    @Override
    public Map<String, Object> queryUserAndRole(String username, Integer page, Integer size) {
        if(username==null){
            username="";
        }
        username = "%"+username+"%";

        List<UserRoleDTO> list = userDao.findUserAndRole(username,(page-1)*size,size);
        Integer total = userDao.findAllUserByUsername(username).size();
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("total",total);
        return map;
    }

    @Override
    public void deleteUser(Integer uid) {
        userDao.deleteById(uid);
    }

    @Override
    public void editUser(UserVo userVo) {
        SysUser user = new SysUser();
        Integer uid = userVo.getId();
        user.setUserId(uid);
        user.setUsername(userVo.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userVo.getPassword()));
        user.setNickname(user.getNickname());
        userDao.update(user);
        List<SysUserRole> list = new ArrayList<>();
        //删除所有的关联角色
        sysUserRoleDao.deleteByUid(uid);
        //重新添加
        if(userVo.getRoles()!=null){
            userVo.getRoles().forEach(integer -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUid(uid);
                sysUserRole.setRid(integer);
                list.add(sysUserRole);
            });
        }
        sysUserRoleDao.insertBatch(list);
    }
}

