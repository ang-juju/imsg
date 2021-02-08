package com.css.tzi.imsgserver.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket服务端
 *
 * @author LiuTao
 * @date 2020/01/05
 */
@Slf4j
@Component
@ServerEndpoint("/websocket/{userId}")
public class WebSocketEndpoint {
    private static final String HEART_BEAT_WORD = "HEART_BEAT";
    private static final ConcurrentHashMap<String, Session> SESSION_POOL = new ConcurrentHashMap<>();
    private String userId;
    private Session session;

    /**
     * 广播消息
     *
     * @param message 消息内容
     */
    public static void broadMessage(String message) {
        log.debug("websocket广播消息({}):\n{}", SESSION_POOL.size(), message);
        Collection<Session> sessions = SESSION_POOL.values();
        for (Session session : sessions) {
            if (session.isOpen()) {
                session.getAsyncRemote().sendText(message);
            }
        }
    }

    /**
     * 单点消息
     *
     * @param userId  用户id
     * @param message 消息内容
     */
    public static void singleMessage(String userId, String message) {
        Session userSession = SESSION_POOL.get(userId);
        if (userSession != null && userSession.isOpen()) {
            log.debug("websocket单点消息({}):\n{}...", userId, message);
            userSession.getAsyncRemote().sendText(message);
        }
    }

    /**
     * 单点消息(多人)
     *
     * @param userIds 用户ids
     * @param message 消息内容
     */
    public static void multipleMessage(String[] userIds, String message) {
        for (String id : userIds) {
            singleMessage(id, message);
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId) throws IOException {
        log.debug("新的websocket接入:{}...", userId);
        Session userSession = SESSION_POOL.get(userId);
        if (userSession != null) {
            log.debug("已存在的用户session:{}...断开处理...", userId);
            userSession.close();
        }
        this.userId = userId;
        this.session = session;
        SESSION_POOL.put(userId, session);
        log.debug("已接入:{}...当前总数为:{}", userId, SESSION_POOL.size());
    }

    @OnClose
    public void onClose() {
        SESSION_POOL.remove(userId);
        log.debug("websocket连接断开:{}...当前总数为:{}...", userId, SESSION_POOL.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.debug("websocket收到客户端消息({}):{}", userId, message);
        if (HEART_BEAT_WORD.equals(message)) {
            session.getAsyncRemote().sendText(HEART_BEAT_WORD);
        }
    }

    @OnError
    public void onError(Throwable throwable) {
        log.error("websocket异常:{}", userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WebSocketEndpoint messageWebSocket = (WebSocketEndpoint) o;
        return session.equals(messageWebSocket.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session);
    }
}
