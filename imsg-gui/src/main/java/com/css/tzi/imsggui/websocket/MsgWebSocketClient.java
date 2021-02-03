package com.css.tzi.imsggui.websocket;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * WebSocket客户端
 *
 * @author LiuTao
 * @date 2021/1/25
 */
@Slf4j
public final class MsgWebSocketClient extends WebSocketClient {
    /**
     * 心跳检测时间间隔
     */
    private static final int HEART_BEAT_PERIOD = 60000;
    private static final String HEART_BEAT_WORD = "HEART_BEAT";
    /**
     * 心跳检测Timer
     */
    private static Timer timer;
    /**
     * 单例客户端
     */
    private static MsgWebSocketClient webSocketClient;

    private MsgWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    /**
     * 开启websocket连接
     *
     * @param serverUri 服务端uri
     * @throws URISyntaxException uri语法错误
     */
    public static void connect(String serverUri) throws URISyntaxException {
        if (webSocketClient == null) {
            webSocketClient = new MsgWebSocketClient(new URI(serverUri));
            webSocketClient.connect();
        } else {
            destroy();
            connect(serverUri);
        }
    }

    /**
     * 销毁websocket客户端
     */
    public static void destroy() {
        if (webSocketClient != null) {
            webSocketClient.close();
        }
    }

    @Override
    public void onOpen(ServerHandshake shake) {
        if (log.isDebugEnabled()) {
            log.debug("websocket客户端连接到服务端...启动心跳检测");
        }
        synchronized (this) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    log.debug("websocket客户端发送心跳检测...");
                    webSocketClient.send(HEART_BEAT_WORD);
                }
            }, HEART_BEAT_PERIOD, HEART_BEAT_PERIOD);
        }
    }

    @Override
    public void onMessage(String paramString) {
        if (log.isDebugEnabled()) {
            log.debug("websocket客户端收到消息...\n{}", paramString);
        }
        if (HEART_BEAT_WORD.equals(paramString)) {
            return;
        }
        OnMessageService.handle(paramString);
    }

    @Override
    public void onClose(int paramInt, String paramString, boolean paramBoolean) {
        if (log.isDebugEnabled()) {
            log.debug("websocket客户端关闭...");
        }
        synchronized (this) {
            webSocketClient = null;
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        }
    }

    @Override
    public void onError(Exception e) {
        log.error("websocket客户端异常" + e);
    }
}
