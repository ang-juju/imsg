package com.css.tzi.imsggui.gui;

import com.css.tzi.imsggui.gui.login.LoginFrame;
import com.css.tzi.imsggui.gui.tray.MsgTrayIcon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * gui入口
 *
 * @author LiuTao
 * @date 2021/1/25
 */
@Controller
@Slf4j
public class GuiAccess {

    @Autowired
    private LoginFrame loginFrame;
    @Autowired
    private MsgTrayIcon trayIcon;

    public void start() {
        loginFrame.setVisible(true);
        trayIcon.bindWindow(loginFrame);
    }
}
