package com.css.tzi.imsggui.websocket;

import org.java_websocket.handshake.ServerHandshake;

/**
 * websocket客户端拓展处理接口
 *
 * @author LiuTao
 * @date 2021/2/3
 */
public interface WebSocketHandler {
    default void onOpen(ServerHandshake shake) {
    }

    default void onMessage(String paramString) {
    }

    default void onClose(int paramInt, String paramString, boolean paramBoolean) {
    }

    default void onError(Exception e) {
    }
}
