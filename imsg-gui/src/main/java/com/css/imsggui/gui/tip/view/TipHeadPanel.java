package com.css.imsggui.gui.tip.view;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * TipDialog头面板
 *
 * @author LiuTao
 * @date 2021/1/27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TipHeadPanel extends JPanel {
    private JLabel titleLabel;
    private JLabel closeLabel;

    public TipHeadPanel(String title) {
        super(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setBackground(new Color(185, 197, 208));

        titleLabel = new JLabel(title);
        titleLabel.setPreferredSize(new Dimension(250, 35));
        titleLabel.setVerticalTextPosition(JLabel.CENTER);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setFont(new Font("宋体", Font.PLAIN, 14));
        titleLabel.setForeground(Color.black);

        closeLabel = new JLabel(" x");
        closeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        closeLabel.setPreferredSize(new Dimension(20, 20));
        closeLabel.setVerticalTextPosition(JLabel.CENTER);
        closeLabel.setHorizontalTextPosition(JLabel.CENTER);
        closeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeLabel.setToolTipText("关闭");
        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeLabel.setBorder(BorderFactory.createLineBorder(Color.gray));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeLabel.setBorder(null);
            }
        });
        add(titleLabel);
        add(closeLabel);

    }
}
