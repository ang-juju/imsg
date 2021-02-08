package com.css.tzi.imsgserver.message.service;

import com.alibaba.fastjson.JSON;
import com.css.tzi.imsgserver.message.entity.MsgEntity;
import com.css.tzi.imsgserver.websocket.WebSocketEndpoint;
import org.springframework.stereotype.Service;

/**
 * redis订阅消息处理
 *
 * @author LiuTao
 * @date 2021/2/8
 */
@Service
public class MsgHandleDelegate {

    /**
     * 广播消息处理器
     *
     * @param msgEntity 消息
     */
    public void broadMessage(MsgEntity msgEntity) {
        WebSocketEndpoint.broadMessage(JSON.toJSONString(msgEntity));
    }

    /**
     * 定向投递消息处理器
     *
     * @param msgEntity 消息
     */
    public void postMessage(MsgEntity msgEntity) {
        String[] receiver = msgEntity.getReceiver();
        WebSocketEndpoint.multipleMessage(receiver, JSON.toJSONString(msgEntity));
    }
}
