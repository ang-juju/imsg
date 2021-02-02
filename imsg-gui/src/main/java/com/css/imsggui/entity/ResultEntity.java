package com.css.imsggui.entity;

import lombok.Data;

/**
 * 服务端http请求结果实体
 *
 * @author LiuTao
 * @date 2021/1/25
 */
@Data
public class ResultEntity {
    private Integer code;
    private String msg;
    private String data;

}
