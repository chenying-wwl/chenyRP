package com.java2023.exam12.bproject.service.Impl;

import com.java2023.exam12.bproject.dao.TbMaterialTypeDao;
import com.java2023.exam12.bproject.dao.TbMaterialsDao;
import com.java2023.exam12.bproject.entity.TbMaterials;
import com.java2023.exam12.bproject.entity.TbWarehouse;
import com.java2023.exam12.bproject.service.TbMaterialsService;
import com.java2023.exam12.bproject.vo.MaterialVo;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.java2023.exam12.bproject.entity.table.TbMaterialsTableDef.TB_MATERIALS;

@Service
public class MaterialService implements TbMaterialsService {
    @Resource
    TbMaterialsDao tbMaterialsDao;
    @Resource
    TbMaterialTypeDao tbMaterialTypeDao;
    @Override
    public TbMaterials queryById(Integer mid) {
        return null;
    }

    @Override
    public TbMaterials queryByName(String warehouseName) {

        return null;
    }

    @Override
    public List<TbMaterials> queryAll() {
        return  tbMaterialsDao.selectAll();
    }

    @Override
    public void add(MaterialVo materialVo) {
        TbMaterials tbMaterials1 = tbMaterialsDao.selectOneByQuery(new QueryWrapper()
                .where(TB_MATERIALS.M_NAME.eq(materialVo.getMName())));
        if(tbMaterials1!=null){
            throw new RuntimeException("该材料已经存在");
        }

        //将自定义生成对应产品编号
        Integer num = null;
        String typeCode = tbMaterialTypeDao.selectOneById(materialVo.getMTypeId()).getTypeCode();
        StringBuilder sb = new StringBuilder(typeCode);
        //关键是这个ID，应该取每个类型有序的编号
        List list = tbMaterialsDao.selectListByQuery(new QueryWrapper()
                .where(TB_MATERIALS.M_TYPE_ID.eq(materialVo.getMTypeId())));
        if(list==null){
            num=1;
        }else {
            num=list.size()+1;
        }
        sb.append("-");
        sb.append(String.format("%03d", num));


        TbMaterials tbMaterials = new TbMaterials();
        tbMaterials.setMCode(sb.toString());
        tbMaterials.setMName(materialVo.getMName());
        tbMaterials.setDescription(materialVo.getDescription());
        tbMaterials.setUnit(materialVo.getUnit());
        tbMaterials.setMTypeId(materialVo.getMTypeId());
        tbMaterialsDao.insert(tbMaterials);
    }

    @Override
    public boolean deleteById(Integer mid) {
        return false;
    }
}
