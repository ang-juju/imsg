package com.css.imsggui.entity;

import lombok.Data;

/**
 * websocket消息实体
 *
 * @author LiuTao
 * @date 2021/1/28
 */
@Data
public class MsgEntity {
    private String title;
    private String content;
    private String url;
}
