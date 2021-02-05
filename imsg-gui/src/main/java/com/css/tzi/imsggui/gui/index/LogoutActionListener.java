package com.css.tzi.imsggui.gui.index;

import com.css.tzi.imsggui.gui.login.LoginFrame;
import com.css.tzi.imsggui.gui.tray.MsgTrayIcon;
import com.css.tzi.imsggui.websocket.MsgWebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 登出事件
 *
 * @author LiuTao
 * @date 2021/2/4
 */
@Controller
public class LogoutActionListener implements ActionListener {

    @Autowired
    private IndexFrame indexFrame;
    @Autowired
    private LoginFrame loginFrame;
    @Autowired
    private MsgTrayIcon trayIcon;

    @PostConstruct
    public void bind() {
        indexFrame.bindLogoutButtonActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MsgWebSocketClient.destroy();
        indexFrame.setVisible(false);
        loginFrame.setVisible(true);
        trayIcon.bindWindow(loginFrame);
    }
}
