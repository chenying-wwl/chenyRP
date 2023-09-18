package com.java2023.exam12.bproject.service.impl;

import com.java2023.exam12.bproject.dao.SysRoleDao;
import com.java2023.exam12.bproject.dao.SysRolePermissionDao;
import com.java2023.exam12.bproject.entity.SysRole;
import com.java2023.exam12.bproject.entity.SysRolePermission;
import com.java2023.exam12.bproject.service.RoleService;
import com.java2023.exam12.bproject.vo.RoleVo;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.java2023.exam12.bproject.entity.table.SysRolePermissionTableDef.SYS_ROLE_PERMISSION;
import static com.java2023.exam12.bproject.entity.table.SysRoleTableDef.SYS_ROLE;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    SysRoleDao roleDao;
    @Resource
    SysRolePermissionDao sysRolePermissionDao;
    @Override
    public void add(SysRole role) {
        roleDao.insert(role);
    }

    @Override
    public Map<String, Object> query(String rname, Integer pageIndex, Integer pageSize) {
        if(rname==null){
            rname="";
        }
        rname = "%"+rname+"%";
        QueryWrapper query=new QueryWrapper()
                .where(SYS_ROLE.RNAME.like(rname));
        Page<SysRole> page = roleDao.paginate(pageIndex,pageSize,query);
        Map<String,Object> map = new HashMap<>();
        //每页的数据
        List<SysRole> list = page.getRecords();
        map.put("list",list);
        //获取总数
        Long total = page.getTotalRow();
        map.put("total",total);
        return map;
    }



    @Override
    public void deleteById(Integer rid) {
        roleDao.deleteById(rid);
    }

    @Override
    @Transactional
    public void grantPerm(RoleVo roleVo) {
        //1,删除角色之前的权限
        sysRolePermissionDao.deleteByQuery(new QueryWrapper().where(SYS_ROLE_PERMISSION.ROLE_ID.eq(roleVo.getRid())));
        //2，添加新的权限
        List<SysRolePermission> list = new ArrayList<>();
        for (Integer permId :roleVo.getPerms()) {
            SysRolePermission rolePermission = new SysRolePermission();
            rolePermission.setRoleId(roleVo.getRid());
            rolePermission.setPermissionId(permId);

            list.add(rolePermission);
        }
        //批量插入
        sysRolePermissionDao.insertBatch(list);
    }

    @Override
    public RoleVo getRole(Integer rid) {

        return QueryChain.of(roleDao)
                .select(
                        SYS_ROLE.RID,
                        SYS_ROLE_PERMISSION.PERMISSION_ID.as("perms")
                        )
                .from(SYS_ROLE)
                .where(SYS_ROLE.RID.eq(rid))
                .leftJoin(SYS_ROLE_PERMISSION)
                .on(SYS_ROLE.RID.eq(SYS_ROLE_PERMISSION.ROLE_ID))
                .oneAs(RoleVo.class);

    }


}
