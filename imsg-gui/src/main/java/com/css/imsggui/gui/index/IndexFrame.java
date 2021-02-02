package com.css.imsggui.gui.index;

import com.css.imsggui.config.AppCtxUtil;
import com.css.imsggui.gui.WindowManager;
import com.css.imsggui.gui.login.view.LoginFrame;
import com.css.imsggui.websocket.MsgWebSocketClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
    private static final int WINDOW_WIDTH = 600;
    /**
     * 窗体高度
     */
    private static final int WINDOW_HEIGHT = 600;


    public IndexFrame() {
        setTitle("消息历史");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("您好");
        welcomeLabel.setBounds(30, 5, 180, 30);
        welcomeLabel.setBackground(Color.BLACK);
        add(welcomeLabel);

        JButton logoutButton = new JButton("注销");
        logoutButton.setBounds(530, 5, 60, 30);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MsgWebSocketClient.destroy();
                setVisible(false);
                LoginFrame loginFrame = AppCtxUtil.getBean(LoginFrame.class);
                loginFrame.setVisible(true);
                AppCtxUtil.getBean(WindowManager.class).setActiveWindow(loginFrame);
            }
        });
        add(logoutButton);

        /*列表数据*/
        String[] tableHead = {"标题", "类型", "内容"};
        String[][] tableData = {{"测试", "系统消息", "中午吃什么"}, {"待办提醒", "系统", "请办理"}, {"发发发", "哈哈哈", "cccc"}};
        JTable jTable = new JTable(tableData, tableHead);

        jTable.setEnabled(false);
        //设置列宽自动调整策略
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //开启自动创建排序器
        jTable.setAutoCreateRowSorter(true);
        //创建滚动panel容器
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(10, 40, 580, 500);
        add(jScrollPane);
    }

    public static void main(String[] args) {
        IndexFrame indexFrame = new IndexFrame();
        indexFrame.setVisible(true);
    }
}
