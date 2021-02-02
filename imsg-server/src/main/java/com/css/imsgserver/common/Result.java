package com.css.imsgserver.common;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;


/**
 * 封装的统一返回数据结构
 *
 * @author LiuTao
 * @date 2020/06/16
 */
public final class Result extends HashMap<String, Object> {
    public static final String CODE = "code";
    public static final String MSG = "msg";
    public static final String DATA = "data";
    private static final long serialVersionUID = 1L;

    private Result() {
        put(CODE, 0);
        put(MSG, "success");
        put(DATA, null);
    }

    public static Result error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
    }

    public static Result error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static Result error(int code, String msg) {
        Result result = new Result();
        result.put(CODE, code);
        result.put(MSG, msg);
        return result;
    }

    public static Result ok() {
        return new Result();
    }

    public static Result ok(String msg) {
        Result result = new Result();
        result.put(MSG, msg);
        return result;
    }

    public static Result ok(Object data) {
        Result result = new Result();
        result.put(DATA, data);
        return result;
    }

    public static Result ok(Map<String, Object> map) {
        Result result = new Result();
        result.putAll(map);
        return result;
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
