package com.css.tzi.imsggui.gui;

import com.css.tzi.imsggui.gui.login.view.LoginFrame;
import com.css.tzi.imsggui.gui.tray.MsgTrayIcon;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * gui入口
 *
 * @author LiuTao
 * @date 2021/1/25
 */
@Controller
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
