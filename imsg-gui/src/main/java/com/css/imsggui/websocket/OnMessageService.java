package com.css.imsggui.websocket;

import com.alibaba.fastjson.JSON;
import com.css.imsggui.entity.MsgEntity;
import com.css.imsggui.gui.tip.TipUtil;

/**
 * 处理服务端websocket消息
 *
 * @author LiuTao
 * @date 2021/2/2
 */
public class OnMessageService {
    public static void handle(String msgStr) {
        MsgEntity msgEntity = JSON.parseObject(msgStr, MsgEntity.class);
        TipUtil.show(msgEntity.getTitle(), msgEntity.getContent(), msgEntity.getUrl());
    }
}
