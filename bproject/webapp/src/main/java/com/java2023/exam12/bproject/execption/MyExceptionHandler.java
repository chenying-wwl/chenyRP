package com.java2023.exam12.bproject.execption;

import com.java2023.exam12.bproject.vo.ResultVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

/**
 * 公众号：耀说编程
 * 微信：wx_aya4
 */
@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResultVo handleAccessException(AccessDeniedException e){
        return ResultVo.fail(e.getMessage(),null);
    }
    @ExceptionHandler(value = RuntimeException.class)
    public ResultVo handleException(RuntimeException e) {
        return ResultVo.fail(e.getMessage(),null);
    }
}
