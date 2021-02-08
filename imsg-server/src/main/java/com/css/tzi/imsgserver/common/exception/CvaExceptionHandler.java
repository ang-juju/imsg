package com.css.tzi.imsgserver.common.exception;

import com.css.tzi.imsgserver.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 *
 * @author LiuTao
 * @date 2020/06/16
 */
@RestControllerAdvice
@Slf4j
public class CvaExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(CvaException.class)
    public Result handleCvaException(CvaException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(NoHandlerFoundException e) {
        return Result.error(HttpStatus.NOT_FOUND.value(), "请求路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error();
    }
}
