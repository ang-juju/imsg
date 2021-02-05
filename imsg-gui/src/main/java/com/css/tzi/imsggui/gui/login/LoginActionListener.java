package com.css.tzi.imsggui.gui.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.css.tzi.imsggui.config.SystemConfig;
import com.css.tzi.imsggui.entity.ResultEntity;
import com.css.tzi.imsggui.entity.UserInfoEntity;
import com.css.tzi.imsggui.gui.index.IndexFrame;
import com.css.tzi.imsggui.gui.tray.MsgTrayIcon;
import com.css.tzi.imsggui.websocket.MsgWebSocketClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * 登陆按钮事件监听器
 *
 * @author LiuTao
 * @date 2021/1/29
 */
@Slf4j
@Controller
public class LoginActionListener implements ActionListener {

    @Autowired
    private LoginFrame loginFrame;
    @Autowired
    private IndexFrame indexFrame;
    @Autowired
    private MsgTrayIcon trayIcon;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SystemConfig systemConfig;

    @PostConstruct
    public void bind() {
        loginFrame.bindLoginButtonActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Map<String, String> authData = loginFrame.getAuthData();
        String userName = authData.get("userName");
        String password = authData.get("password");
        if (!StringUtils.hasText(userName) || !StringUtils.hasText(password)) {
            JOptionPane.showMessageDialog(null, "用户名密码不允许为空", "提醒", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", userName);
        jsonObject.put("password", password);
        try {
            String result = restTemplate.postForObject(
                    systemConfig.getRestfulUrl() + systemConfig.getLoginPath()
                    , jsonObject.toJSONString(), String.class);
            if (result == null) {
                throw new RuntimeException("null result");
            }
            ResultEntity resultEntity = JSON.parseObject(result, ResultEntity.class);
            if (0 == resultEntity.getCode()) {
                UserInfoEntity userInfoEntity = JSON.parseObject(resultEntity.getData(), UserInfoEntity.class);
                MsgWebSocketClient.connect(systemConfig.getWebsocketUrl() + userInfoEntity.getId());
                loginFrame.setVisible(false);
                indexFrame.setVisible(true);
                indexFrame.setRealName(userInfoEntity.getRealName());
                trayIcon.bindWindow(indexFrame);
            } else {
                JOptionPane.showMessageDialog(null, resultEntity.getMsg(), "提醒", JOptionPane.ERROR_MESSAGE);
            }
        } catch (RuntimeException | URISyntaxException ex) {
            log.error("服务调用异常", ex);
            JOptionPane.showMessageDialog(null, "无法连接服务器...", "提醒", JOptionPane.ERROR_MESSAGE);
        }
    }
}
