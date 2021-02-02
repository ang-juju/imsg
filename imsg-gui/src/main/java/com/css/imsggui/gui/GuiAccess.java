package com.css.imsggui.gui;

import com.css.imsggui.gui.login.view.LoginFrame;
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

}
