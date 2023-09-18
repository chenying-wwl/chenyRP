package com.java2023.exam12.bproject.service.impl;

import com.java2023.exam12.bproject.dao.SysPermissionDao;
import com.java2023.exam12.bproject.dto.PermissionDTO;
import com.java2023.exam12.bproject.entity.SysPermission;
import com.java2023.exam12.bproject.entity.SysRole;
import com.java2023.exam12.bproject.service.PermService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.java2023.exam12.bproject.entity.table.SysPermissionTableDef.SYS_PERMISSION;
import static com.java2023.exam12.bproject.entity.table.SysRoleTableDef.SYS_ROLE;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
@Service
public class PermServiceImpl implements PermService {
    @Resource
    SysPermissionDao permissionDao;
    @Override
    public void add(SysPermission permission) {
        if(permission.getParentId()==null){
            permission.setPermId(0);//根结点
        }
        permissionDao.insertOrUpdate(permission);
    }

    @Override
    public List<PermissionDTO> list() {
        return findByParent(0);
    }


    @Override
    public void deleteById(Integer id) {

        List<PermissionDTO> list = findByParent(id);
        if(list.size()>0){
            throw new RuntimeException("请先删除当前结点的子结点");
        }
        permissionDao.deleteById(id);
    }

    @Override
    public SysPermission findById(Integer id) {
        return permissionDao.selectOneById(id);
    }


    @Override
    public Map<String, Object> query(Integer pageIndex, Integer pageSize) {
        Map<String,Object> map = new HashMap<>();
        List<PermissionDTO> list = findByParent(0);
        // 计算起始索引
//        int startIndex = (pageIndex - 1) * pageSize;
        map.put("total",list.size());
        // 截取子列表
//        List<PermissionDTO> pageData = list.subList(startIndex, Math.min(startIndex + pageSize, list.size()));
        map.put("list",list);
        return map;
    }

    public List<PermissionDTO> findByParent(int parentId){
        //查询根据父类结点查询子结点
        List<PermissionDTO> list = permissionDao.selectListByQueryAs(
                new QueryWrapper().where(SYS_PERMISSION.PARENT_ID.eq(parentId)),
                PermissionDTO.class
        );
        //遍历所有的子结点，
        for (PermissionDTO permissionDTO :list) {
            //再以子结点id作为parentId进行查询
            List<PermissionDTO> children = findByParent(permissionDTO.getPermId());
            if(children!=null && children.size()>0){
                //将所有的子结点放入到父类结点中
                permissionDTO.getChildren().addAll(children);
            }
        }
        return list;
    }
}
