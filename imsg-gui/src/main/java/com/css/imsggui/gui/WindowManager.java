package com.css.imsggui.gui;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.HashMap;


/**
 * 窗体管理器
 *
 * @author LiuTao
 * @date 2021/1/29
 */
@Component
public class WindowManager extends HashMap<String, Window> {
    // 当前激活的窗体
    private Window activeWindow;

    public Window getActiveWindow() {
        return activeWindow;
    }

    public void setActiveWindow(Window activeWindow) {
        this.activeWindow = activeWindow;
    }


}
