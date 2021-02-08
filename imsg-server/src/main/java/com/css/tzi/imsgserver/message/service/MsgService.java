package com.css.tzi.imsgserver.message.service;

import com.css.tzi.imsgserver.message.entity.MsgEntity;

/**
 * 发送消息服务接口
 *
 * @author LiuTao
 * @date 2021/2/8
 */
public interface MsgService {

    /**
     * 投放消息
     *
     * @param msgEntity 消息实体
     */
    void post(MsgEntity msgEntity);

    /**
     * 广播消息
     *
     * @param msgEntity 消息实体
     */
    void broad(MsgEntity msgEntity);
}
