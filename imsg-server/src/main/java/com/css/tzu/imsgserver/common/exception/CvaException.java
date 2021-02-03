package com.css.tzu.imsgserver.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 自定义异常
 *
 * @author LiuTao
 * @date 2020/03/16
 */
@Getter
public class CvaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final int code;
    private final String msg;

    public CvaException(String msg) {
        super(msg);
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.msg = msg;
    }

    public CvaException(String msg, Throwable e) {
        super(msg, e);
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.msg = msg;
    }

    public CvaException(int code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public CvaException(int code, String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}

