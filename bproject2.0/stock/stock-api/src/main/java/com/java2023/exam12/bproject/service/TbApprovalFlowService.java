package com.java2023.exam12.bproject.service;

import com.java2023.exam12.bproject.entity.TbApprovalFlow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 * 审批流程表(TbApprovalFlow)表服务接口
 *
 * @author makejava
 * @since 2023-08-23 21:49:38
 */
public interface TbApprovalFlowService {

    //查询
    public Map<String,Object> query(String username,Integer pageIndex, Integer pageSize);
    //批准申请
    public void approval(Integer id,Integer isApproval);
    public void  deletetbApproval(Integer id);

}
