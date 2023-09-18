package com.java2023.exam12.bproject.service;

import com.java2023.exam12.bproject.entity.TbRecords;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 * 出入库记录表(TbRecords)表服务接口
 *
 * @author makejava
 * @since 2023-08-24 19:48:35
 */
public interface TbRecordsService {

        public Map<String,Object> query(String materialName,Integer pageIndex, Integer pageSize);
}
