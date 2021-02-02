package com.css.imsggui.gui.login.view;

import com.css.imsggui.gui.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.HashMap;

/**
 * 登陆窗口
 *
 * @author LiuTao
 * @date 2021/1/22
 */
@Slf4j
@Component
public class LoginFrame extends JFrame {
    // 用户名输入框
    private final JTextField usernameTextField;
    // 密码输入框
    private final JPasswordField passwordField;
    // 登陆按钮
    private final JButton loginButton;


    public LoginFrame() {
        // 设置标题
        setTitle("请登录");
        // 关闭按钮设置为隐藏
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        // 设置大小
        setSize(300, 150);
        // 设置居中显示
        setLocationRelativeTo(null);
        // 不可拖拽大小
        setResizable(false);
        // 设置无布局管理器
        setLayout(null);
        setIconImage(ImageUtil.getImage("icon"));


        /* 用户名部分 */
        JLabel usernameLabel = new JLabel("用户名：");
        usernameLabel.setBounds(10, 10, 200, 18);


        usernameTextField = new JTextField();
        // 设置用户名框的宽，高，x值，y值
        usernameTextField.setBounds(80, 10, 150, 18);

        /* 密码部分 */
        JLabel passwordLabel = new JLabel("密   码：");
        passwordLabel.setBounds(10, 50, 200, 18);
        // 密码输入框
        passwordField = new JPasswordField();
        //设置密码框的宽，高，x值，y值
        passwordField.setBounds(80, 50, 150, 18);

        /* 添加组件到登陆窗体中 */
        add(usernameLabel);
        add(usernameTextField);
        add(passwordLabel);
        add(passwordField);

        /* 确定按钮 */
        loginButton = new JButton("确定");
        loginButton.setBounds(80, 80, 60, 18);
        add(loginButton);

        /*重置按钮*/
        final JButton button = new JButton("重置");
        button.addActionListener(arg0 -> {
            usernameTextField.setText("");
            passwordField.setText("");
        });
        button.setBounds(150, 80, 60, 18);
        add(button);
    }

    /**
     * 获取鉴权数据
     *
     * @return 鉴权数据对象
     */
    public Object getAuthData() {
        String userName = usernameTextField.getText();
        String password = new String(passwordField.getPassword());
        return new HashMap<String, String>() {{
            put("userName", userName);
            put("password", password);
        }};
    }

    /**
     * 获取登陆按钮
     *
     * @return 登陆按钮对象
     */
    public JButton getLoginButton() {
        return loginButton;
    }

}

