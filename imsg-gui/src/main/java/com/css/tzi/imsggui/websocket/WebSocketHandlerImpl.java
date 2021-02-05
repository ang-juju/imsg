package com.css.tzi.imsggui.websocket;

import com.alibaba.fastjson.JSON;
import com.css.tzi.imsggui.entity.MsgEntity;
import com.css.tzi.imsggui.gui.index.IndexFrame;
import com.css.tzi.imsggui.gui.login.LoginFrame;
import com.css.tzi.imsggui.gui.tip.TipUtil;
import com.css.tzi.imsggui.gui.tray.MsgTrayIcon;
import com.css.tzi.imsggui.gui.util.AudioLoader;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * websocket客户端拓展实现类
 *
 * @author LiuTao
 * @date 2021/2/2
 */
@Component
public class WebSocketHandlerImpl implements WebSocketHandler {

    @Autowired
    private MsgTrayIcon trayIcon;
    @Autowired
    private IndexFrame indexFrame;
    @Autowired
    private LoginFrame loginFrame;

    @Override
    public void onMessage(WebSocketClient webSocketClient, String paramString) {
        MsgEntity msgEntity = JSON.parseObject(paramString, MsgEntity.class);
        TipUtil.show(msgEntity.getTitle(), msgEntity.getContent(), msgEntity.getUrl());
        trayIcon.startFlash();
        AudioLoader.play("tip");
    }

    @Override
    public void onClose(int paramInt, String paramString, boolean paramBoolean) {
        indexFrame.setStatus("已断开");
    }

    @Override
    public void onReconnect(int reconnectCount) {
        indexFrame.setStatus("已断开，正在重连...");
    }

    @Override
    public void onOpen(WebSocketClient webSocketClient, ServerHandshake shake) {
        indexFrame.setStatus("已连接");
    }
}
