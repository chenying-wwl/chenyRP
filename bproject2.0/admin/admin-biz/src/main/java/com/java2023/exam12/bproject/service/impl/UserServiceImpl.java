package com.java2023.exam12.bproject.service.impl;

import com.java2023.exam12.bproject.dao.SysUserDao;
import com.java2023.exam12.bproject.dao.SysUserRoleDao;
import com.java2023.exam12.bproject.dto.UserDTO;
import com.java2023.exam12.bproject.entity.SysPermission;
import com.java2023.exam12.bproject.entity.SysUser;
import com.java2023.exam12.bproject.entity.SysUserRole;
import com.java2023.exam12.bproject.service.UserService;
import com.java2023.exam12.bproject.vo.UserVo;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

import static com.java2023.exam12.bproject.entity.table.SysRoleTableDef.SYS_ROLE;
import static com.java2023.exam12.bproject.entity.table.SysUserRoleTableDef.SYS_USER_ROLE;
import static com.java2023.exam12.bproject.entity.table.SysUserTableDef.SYS_USER;

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
    public Map<String, Object> queryUserAndRole(String username, Integer page, Integer size) {
        if(username==null){
            username="";
        }
        username = "%"+username+"%";

        List<UserDTO> list = userDao.findUserAndRole(username,(page-1)*size,size);
        Integer total = userDao.findAllUserByUsername(username).size();
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("total",total);
        return map;
    }
    @Override
    @Transactional
    public void add(UserVo user) {
        SysUser u = new SysUser();
        u.setUsername(user.getUsername());
        u.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        //设置默认的昵称
        if(user.getNickname()==null&&user.getPassword().equals("")){
            u.setNickname(user.getUsername());
        }else{
            u.setNickname(user.getNickname());
        }
        int id =  userDao.insert(u);

        //插入用户和角色之间的关系
        if(user.getRoles()!=null){
            List< SysUserRole> userRoles = new ArrayList<>();
            for(Integer rid : user.getRoles()){
                SysUserRole userRole = new SysUserRole();
                userRole.setRid(rid);
                userRole.setUid(u.getUserId());
                userRoles.add(userRole);
            }
            sysUserRoleDao.insertBatch(userRoles);
        }
    }

    @Override
    public void deleteById(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    public UserDTO findByName(String name) {
        //通过mybatisPlus的连表查询实现
       UserDTO userDTO=  QueryChain.of(userDao).
                select().from(SYS_USER)
                .leftJoin(SYS_USER_ROLE).on(SYS_USER.USER_ID.eq(SYS_USER_ROLE.UID))
                .where(SYS_USER.USERNAME.eq(name))
                .leftJoin(SYS_ROLE).on(SYS_ROLE.RID.eq(SYS_USER_ROLE.RID))
                .oneAs(UserDTO.class);


        return userDTO;
    }

    @Override
    public void update(UserVo userVo) {
        //更新用户
        SysUser user = new SysUser();
        user.setUserId(userVo.getUserId());//获取id
        user.setNickname(userVo.getNickname());
        user.setUsername(userVo.getUsername());
        userDao.update(user);

        //跟新用户的角色
        //先删除所有的用户角色
        sysUserRoleDao.deleteByQuery(new QueryWrapper().where(SYS_USER_ROLE.UID.eq(userVo.getUserId())));

        //然后插入新的角色
        if(userVo.getRoles()!=null&&userVo.getRoles().size()>0){
            List< SysUserRole> userRoles = new ArrayList<>();
            for(Integer rid : userVo.getRoles()){
                SysUserRole userRole = new SysUserRole();
                userRole.setRid(rid);
                userRole.setUid(userVo.getUserId());
                userRoles.add(userRole);
            }
            sysUserRoleDao.insertBatch(userRoles);
        }
    }
}
