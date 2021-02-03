package com.css.tzi.imsggui.gui.tray;

import com.css.tzi.imsggui.gui.util.ImageLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 系统托盘封装类
 *
 * @author LiuTao
 * @date 2021/1/29
 */
@Slf4j
@Component
public class MsgTrayIcon {
    /**
     * 绑定的窗口
     */
    private Window bindWindow;
    /**
     * 托盘图标对象
     */
    private TrayIcon trayIcon;
    /**
     * 闪烁动画线程
     */
    private final Thread flashThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    trayIcon.setImage(new ImageIcon("").getImage());
                    Thread.sleep(500);
                    trayIcon.setImage(ImageLoader.getImage("icon"));
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    trayIcon.setImage(ImageLoader.getImage("icon"));
                    return;
                }
            }
        }
    });

    public MsgTrayIcon() {
        if (SystemTray.isSupported()) {
            // 获取托盘图标图像
            Image image = ImageLoader.getImage("icon");
            // 创建系统托盘菜单
            PopupMenu popupMenu = new PopupMenu();
            // 添加系统托盘菜单项
            MenuItem openItem = new MenuItem("打开");
            MenuItem exitItem = new MenuItem("退出");
            popupMenu.add(openItem);
            popupMenu.add(exitItem);
            // open事件监听
            openItem.addActionListener(e -> {
                if (bindWindow != null) {
                    bindWindow.setVisible(true);
                }
            });
            // exit事件监听
            exitItem.addActionListener(e -> {
                // 程序退出
                System.exit(0);
            });
            // 创建系统托盘
            this.trayIcon = new TrayIcon(image, "消息提醒系统", popupMenu);
            // 托盘图标自适应尺寸
            this.trayIcon.setImageAutoSize(true);
            this.trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        if (bindWindow != null) {
                            bindWindow.setVisible(true);
                        }
                    }
                }
            });
            //获取系统图盘
            SystemTray systemTray = SystemTray.getSystemTray();
            try {
                systemTray.add(this.trayIcon);
            } catch (AWTException e) {
                log.error("添加托盘图标失败", e);
            }
        } else {
            log.error("当前系统不支持托盘");
        }
    }

    public void bindWindow(Window window) {
        this.bindWindow = window;
    }

    public void startFlash() {
        flashThread.start();
    }

    public void stopFlash() {
        flashThread.interrupt();
    }

}
