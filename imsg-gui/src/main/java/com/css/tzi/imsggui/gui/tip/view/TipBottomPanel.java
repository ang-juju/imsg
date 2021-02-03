package com.css.tzi.imsggui.gui.tip.view;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import java.awt.*;

/**
 * TipDialog底部面板
 *
 * @author LiuTao
 * @date 2021/1/27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TipBottomPanel extends JPanel {
    private JButton viewButton;

    public TipBottomPanel() {
        super(new FlowLayout(FlowLayout.CENTER, 0, 0));
        viewButton = new JButton("查看");
        viewButton.setHorizontalAlignment(SwingConstants.CENTER);
        viewButton.setPreferredSize(new Dimension(60, 30));
        viewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewButton.setContentAreaFilled(false);
        viewButton.setBorder(BorderFactory.createRaisedBevelBorder());
        viewButton.setBackground(Color.gray);
        setBackground(Color.white);
        add(viewButton);
    }
}
