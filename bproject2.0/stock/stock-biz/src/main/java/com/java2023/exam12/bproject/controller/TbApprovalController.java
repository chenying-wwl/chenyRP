package com.java2023.exam12.bproject.controller;

import com.java2023.exam12.bproject.service.TbApprovalFlowService;
import com.java2023.exam12.bproject.vo.ResultVo;
import com.java2023.exam12.bproject.vo.StockOutInVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("tbApproval")
@Api("申请审核")
public class TbApprovalController {
    @Autowired
    TbApprovalFlowService tbApprovalFlowService;

    @PostMapping
    public ResultVo Approval(Integer id,Integer isApproval){
        tbApprovalFlowService.approval(id, isApproval);
        return ResultVo.ok("审核完成",null);
    }
    @GetMapping
    public ResultVo queryByUser(String username,Integer pageIndex, Integer pageSize){
        Map map = tbApprovalFlowService.query(username,pageIndex,pageSize);
        return ResultVo.ok(null,map);
    }
    @DeleteMapping("{id}")
    public ResultVo deleteById(@PathVariable Integer id){
        tbApprovalFlowService.deletetbApproval(id);
        return ResultVo.ok("删除成功",null);
    }
}
