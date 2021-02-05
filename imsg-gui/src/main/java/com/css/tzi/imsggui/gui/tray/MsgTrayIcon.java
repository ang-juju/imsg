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
public final class MsgTrayIcon extends TrayIcon implements Runnable {
    /**
     * 闪烁动画线程
     */
    private Thread flashThread;
    /**
     * 绑定的窗口
     */
    private Window bindWindow;

    public MsgTrayIcon() {
        // 构建托盘图标
        super(ImageLoader.getImage("icon"));
        // 图盘图标提示
        setToolTip("消息提醒系统");
        // 托盘图标自适应尺寸
        setImageAutoSize(true);
        /* 托盘鼠标事件 */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                /* 双击事件 */
                if (e.getClickCount() == MouseEvent.BUTTON2 && bindWindow != null) {
                    // 显示绑定的窗口
                    bindWindow.setVisible(true);
                }
            }
        });
        /* 添加系统托盘菜单项 */
        PopupMenu popupMenu = new PopupMenu();
        MenuItem openItem = new MenuItem("打开");
        MenuItem exitItem = new MenuItem("退出");
        popupMenu.add(openItem);
        popupMenu.add(exitItem);
        setPopupMenu(popupMenu);
        /* open菜单事件监听 */
        openItem.addActionListener(e -> {
            if (bindWindow != null) {
                // 显示绑定的窗体
                bindWindow.setVisible(true);
            }
        });
        /* exit事件监听 */
        exitItem.addActionListener(e ->
                // 程序退出
                System.exit(0)
        );
        /* 托盘图标添加到系统托盘中 */
        if (SystemTray.isSupported()) {
            //获取系统图盘
            SystemTray systemTray = SystemTray.getSystemTray();
            try {
                systemTray.add(this);
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
        if (flashThread == null) {
            flashThread = new Thread(this);
            flashThread.start();
        }
    }

    public void stopFlash() {
        if (flashThread != null) {
            flashThread.interrupt();
            flashThread = null;
        }
    }

    @Override
    public void run() {
        log.debug("托盘开始闪烁");
        Image nonIcon = new ImageIcon("").getImage();
        while (true) {
            try {
                setImage(nonIcon);
                Thread.sleep(500);
                setImage(ImageLoader.getImage("icon"));
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.debug("托盘闪烁停止");
                Thread.currentThread().interrupt();
                setImage(ImageLoader.getImage("icon"));
                break;
            }
        }
    }
}
