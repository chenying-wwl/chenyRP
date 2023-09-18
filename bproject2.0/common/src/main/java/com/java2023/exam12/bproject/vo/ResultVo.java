package com.java2023.exam12.bproject.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo {
    public static final Integer OK = 200;
    public static final Integer FAIL = 400;
    public static final Integer INVALID_TOKEN = 401;
    Integer code;
    String msg;
    Object data;

    public static ResultVo fail(String msg, Object data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(FAIL);
        resultVo.setMsg(msg);
        resultVo.setData(data);
        return resultVo;
    }
    public static ResultVo ok(String msg, Object data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(OK);
        resultVo.setMsg(msg);
        resultVo.setData(data);
        return resultVo;
    }
    public static ResultVo ok(String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(OK);
        resultVo.setMsg(msg);
        return resultVo;
    }

    public static ResultVo okPage(List list,Integer total) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(OK);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("list",list);
        map.put("total",total);
        resultVo.setData(map);
        return resultVo;
    }
}
