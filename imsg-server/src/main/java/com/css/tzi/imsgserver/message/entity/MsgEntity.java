package com.css.tzi.imsgserver.message.entity;

import lombok.Data;

/**
 * 消息实体类
 *
 * @author LiuTao
 * @date 2021/2/8
 */
@Data
public class MsgEntity {
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * web资源地址
     */
    private String toUrl;
    /**
     * 接收人
     */
    private String[] receiver;

}
