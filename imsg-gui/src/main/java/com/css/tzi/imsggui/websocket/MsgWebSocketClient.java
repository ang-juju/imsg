package com.css.tzi.imsggui.websocket;

import com.css.tzi.imsggui.config.AppCtxUtil;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.framing.CloseFrame;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.BeansException;

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
    /**
     * 最大断线重连次数
     */
    private static final int RECONNECT_MAX_COUNT = 30;
    /**
     * 心跳检测口令
     */
    private static final String HEART_BEAT_WORD = "HEART_BEAT";
    /**
     * websocket客户端拓展类
     */
    private static WebSocketHandler webSocketHandler;
    /**
     * 心跳检测Timer
     */
    private static Timer heartBeatTimer;
    /**
     * 断线重连计数器
     */
    private static int reconnectCount;
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
        log.debug("调用创建websocket客户端连接方法...");
        if (webSocketClient == null) {
            webSocketClient = new MsgWebSocketClient(new URI(serverUri));
            log.debug("实例化websocket客户端对象[{}]...", webSocketClient.hashCode());
            try {
                webSocketHandler = AppCtxUtil.getBean(WebSocketHandler.class);
            } catch (BeansException e) {
                log.debug("未找到websocket客户端拓展实现...", e);
            }
            webSocketClient.connect();
        } else {
            log.debug("已存在websocket客户端对象[{}]，调用销毁...", webSocketClient.hashCode());
            destroy();
            connect(serverUri);
        }
    }

    /**
     * 销毁websocket客户端
     */
    public static void destroy() {
        log.debug("调用websocket客户端销毁方法...");
        if (webSocketClient != null) {
            log.debug("清理websocket客户端对象");
            WebSocketClient client = webSocketClient;
            webSocketClient = null;
            client.close();
        }
    }

    @Override
    public void onOpen(ServerHandshake shake) {
        if (log.isDebugEnabled()) {
            log.debug("websocket客户端连接服务器成功...重制重连次数...启动心跳检测");
        }
        // 重置重连次数计数器
        reconnectCount = 0;
        // 重置心跳检测定时器
        heartBeatTimer = new Timer();
        heartBeatTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.debug("websocket客户端发送心跳检测...");
                webSocketClient.send(HEART_BEAT_WORD);
            }
        }, HEART_BEAT_PERIOD, HEART_BEAT_PERIOD);
        if (webSocketHandler != null) {
            webSocketHandler.onOpen(this, shake);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        if (webSocketHandler != null) {
            webSocketHandler.onClose(code, reason, remote);
        }
        // 清理心跳检测定时器
        if (heartBeatTimer != null) {
            log.debug("清理心跳检测定时器");
            heartBeatTimer.cancel();
            heartBeatTimer = null;
        }
        // 正常关闭
        if (CloseFrame.NORMAL == code) {
            log.debug("websocket客户端已经正常关闭...");
        } else {
            // 异常关闭
            log.debug("websocket客户端异常关闭...");
            if (reconnectCount < RECONNECT_MAX_COUNT && this == webSocketClient) {
                log.debug("等待尝试断线重连尝试第{}次", reconnectCount + 1);
                reconnectCount++;
                if (webSocketHandler != null) {
                    webSocketHandler.onReconnect(reconnectCount);
                }
                new Thread(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    if (this == webSocketClient) {
                        reconnect();
                    } else {
                        log.debug("客户端已经被销毁或已被建立，不再进行重连尝试");
                    }
                }).start();
            } else {
                log.debug("超出重连次数，销毁客户端对象");
                webSocketClient = null;
            }
        }
    }

    @Override
    public void onMessage(String message) {
        if (log.isDebugEnabled()) {
            log.debug("websocket客户端收到消息...\n{}", message);
        }
        if (HEART_BEAT_WORD.equals(message)) {
            return;
        }
        if (webSocketHandler != null) {
            webSocketHandler.onMessage(this, message);
        }
    }

    @Override
    public void onError(Exception e) {
        log.error("websocket客户端异常" + e);
        if (webSocketHandler != null) {
            webSocketHandler.onError(this, e);
        }
    }
}
