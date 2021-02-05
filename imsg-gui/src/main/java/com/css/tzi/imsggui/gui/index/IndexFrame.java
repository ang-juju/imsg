package com.css.tzi.imsggui.gui.index;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * 主页窗口
 *
 * @author LiuTao
 * @date 2021/1/25
 */
@Slf4j
@Component
public final class IndexFrame extends JFrame {
    /**
     * 窗体宽度
     */
    private static final int WINDOW_WIDTH = 300;
    /**
     * 窗体高度
     */
    private static final int WINDOW_HEIGHT = 300;
    /**
     * 用户姓名label
     */
    private final JLabel realNameLabel;
    /**
     * 连接状态label
     */
    private final JLabel statusLabel;
    /**
     * 登出button
     */
    private final JButton logoutButton;

    public IndexFrame() {
        /* 窗体属性 */
        setTitle("消息中心");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        /* 北部面板 */
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        // 欢迎语
        JLabel welcomeLabel = new JLabel("您好：", SwingConstants.CENTER);
        welcomeLabel.setPreferredSize(new Dimension(50, 20));
        northPanel.add(BorderLayout.WEST, welcomeLabel);
        // 用户名
        realNameLabel = new JLabel();
        realNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        northPanel.add(BorderLayout.CENTER, realNameLabel);

        logoutButton = new JButton("注销");
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.setFocusPainted(false);
        northPanel.add(BorderLayout.EAST, logoutButton);
        add(BorderLayout.NORTH, northPanel);

        /* 中部内容面板 */

        JTable jTable = new JTable();
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBackground(Color.white);
        add(BorderLayout.CENTER, jScrollPane);

        /*南部面板*/
        JPanel southPanel = new JPanel();
        statusLabel = new JLabel();
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        southPanel.add(statusLabel);
        add(BorderLayout.SOUTH, southPanel);
    }

    /**
     * 设置用户姓名
     *
     * @param realName 用户姓名
     */
    public void setRealName(String realName) {
        realNameLabel.setText(realName);
    }

    /**
     * 绑定登出按钮监听器
     *
     * @param listener 登出按钮监听器
     */
    public void bindLogoutButtonActionListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public void setStatus(String status) {
        statusLabel.setText(status);
    }
}
