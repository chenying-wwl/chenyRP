package com.java2023.exam12.bproject.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
public class ResponseUtils {

    public static void writeJSON(HttpServletResponse response, Object object) throws IOException {{
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().write(JSON.toJSONString(object));
    }}
}
