package com.qingkong.consumer.exception;

import com.qingkong.consumer.common.Result;
import com.qingkong.consumer.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public Result illegalParamException(Exception e){
        log.error("非法参数异常，{}",e);
        return Result.result(ResultEnum.ILLEGAL_PARAM_ERROR);
    }

    @ExceptionHandler(MavenException.class)
    public Result mavenException(MavenException e){
        if(e.getResultEnum() != null){
            return Result.result(e.getResultEnum());
        }
        log.error("业务异常，{}",e);
        return Result.result(1,e.getMessage(),null);
    }

    @ExceptionHandler(Exception.class)
    public Result Exception(Exception e){
        log.error("异常，{}",e);
        return Result.result(ResultEnum.SYS_ERROR);
    }
}
