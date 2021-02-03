package com.css.tzi.imsggui.gui;

import com.css.tzi.imsggui.gui.login.view.LoginFrame;
import com.css.tzi.imsggui.gui.tray.MsgTrayIcon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
    private WindowManager windowManager;


    public void start() {
        windowManager.setActiveWindow(loginFrame);
        loginFrame.setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException {
        MsgTrayIcon msgTrayIcon = new MsgTrayIcon();
        msgTrayIcon.startFlash();
        Thread.sleep(6000);
        msgTrayIcon.stopFlash();
        Thread.sleep(3000);
    }

}
