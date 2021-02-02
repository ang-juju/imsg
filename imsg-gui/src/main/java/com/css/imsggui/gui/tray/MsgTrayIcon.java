package com.css.imsggui.gui.tray;

import com.css.imsggui.gui.WindowManager;
import com.css.imsggui.gui.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 系统托盘
 *
 * @author LiuTao
 * @date 2021/1/29
 */
@Slf4j
@Component
public class MsgTrayIcon {
    /**
     * 构造系统托盘图标
     *
     * @param windowManager 窗体管理器
     */
    public MsgTrayIcon(WindowManager windowManager) {
        if (SystemTray.isSupported()) {
            // 获取托盘图标图像
            Image image = ImageUtil.getImage("icon");
            // 创建系统托盘菜单
            PopupMenu popupMenu = new PopupMenu();
            // 添加系统托盘菜单项
            MenuItem openItem = new MenuItem("打开");
            MenuItem exitItem = new MenuItem("退出");
            popupMenu.add(openItem);
            popupMenu.add(exitItem);
            // open事件监听
            openItem.addActionListener(e -> {
                // 显示绑定的组件
                windowManager.getActiveWindow().setVisible(true);
            });
            // exit事件监听
            exitItem.addActionListener(e -> {
                // 程序退出
                System.exit(0);
            });
            // 创建系统托盘
            TrayIcon trayIcon = new TrayIcon(image, "消息提醒系统", popupMenu);
            // 托盘图标自适应尺寸
            trayIcon.setImageAutoSize(true);
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        windowManager.getActiveWindow().setVisible(true);
                    }
                }
            });
            //获取系统图盘
            SystemTray systemTray = SystemTray.getSystemTray();
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                log.error("添加托盘图标失败", e);
            }
        } else {
            log.error("当前系统不支持托盘");
        }
    }
}
