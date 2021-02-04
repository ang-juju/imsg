package com.css.tzi.imsggui.websocket;

import com.alibaba.fastjson.JSON;
import com.css.tzi.imsggui.config.AppCtxUtil;
import com.css.tzi.imsggui.entity.MsgEntity;
import com.css.tzi.imsggui.gui.tip.TipUtil;
import com.css.tzi.imsggui.gui.tray.MsgTrayIcon;
import com.css.tzi.imsggui.gui.util.AudioLoader;
import org.springframework.stereotype.Component;

/**
 * websocket客户端拓展实现类
 *
 * @author LiuTao
 * @date 2021/2/2
 */
@Component
public class WebSocketHandlerImpl implements WebSocketHandler {

    @Override
    public void onMessage(String paramString) {
        MsgEntity msgEntity = JSON.parseObject(paramString, MsgEntity.class);
        TipUtil.show(msgEntity.getTitle(), msgEntity.getContent(), msgEntity.getUrl());
        MsgTrayIcon bean = AppCtxUtil.getBean(MsgTrayIcon.class);
        bean.startFlash();
        AudioLoader.play("tip");
    }
}
