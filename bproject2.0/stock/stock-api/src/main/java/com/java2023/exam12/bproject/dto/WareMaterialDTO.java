package com.java2023.exam12.bproject.dto;

import com.java2023.exam12.bproject.entity.TbMaterials;
import com.java2023.exam12.bproject.entity.TbWareMaterial;
import com.java2023.exam12.bproject.entity.TbWarehouse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WareMaterialDTO extends TbWareMaterial {
    TbMaterials tbMaterials;
    TbWarehouse tbWarehouse;
}
