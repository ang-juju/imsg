package com.css.tzi.imsggui.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/**
 * websocket客户端拓展处理接口
 *
 * @author LiuTao
 * @date 2021/2/3
 */
public interface WebSocketHandler {
    default void onOpen(WebSocketClient webSocketClient, ServerHandshake shake) {
    }

    default void onMessage(WebSocketClient webSocketClient, String paramString) {
    }

    default void onClose(int paramInt, String paramString, boolean paramBoolean) {
    }

    default void onReconnect(int reconnectCount) {
    }

    default void onError(WebSocketClient webSocketClient, Exception e) {
    }
}
