package com.java2023.exam12.bproject.service.impl;

import com.java2023.exam12.bproject.dao.SysRoleDao;
import com.java2023.exam12.bproject.dto.UserRoleDTO;
import com.java2023.exam12.bproject.entity.SysRole;
import com.java2023.exam12.bproject.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    SysRoleDao sysRoleDao;

    @Override
    public List<SysRole> getAllRole() {
        return sysRoleDao.selectAll();
    }

    @Override
    public Map<String, Object> query(String rname, Integer page, Integer size) {
        if(rname==null){
            rname="";
        }
        rname = "%"+rname+"%";

        List<SysRole> list = sysRoleDao.findRoleByRname(rname,(page-1)*size,size);
        Integer total = sysRoleDao.findAllRoleByRname(rname).size();
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("total",total);
        return map;
    }
}
