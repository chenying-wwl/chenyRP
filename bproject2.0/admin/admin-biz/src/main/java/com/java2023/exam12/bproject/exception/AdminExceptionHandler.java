package com.java2023.exam12.bproject.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.java2023.exam12.bproject.vo.ResultVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.java2023.exam12.bproject.vo.ResultVo.INVALID_TOKEN;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
@RestControllerAdvice
public class AdminExceptionHandler {

    @ExceptionHandler(TokenExpiredException.class)
    public ResultVo handleTokenExpired(TokenExpiredException e){
        ResultVo result = new ResultVo(INVALID_TOKEN,"token已经失效了",null);
        return result;
    }
}
