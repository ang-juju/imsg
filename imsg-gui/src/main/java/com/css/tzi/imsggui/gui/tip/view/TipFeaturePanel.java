package com.css.tzi.imsggui.gui.tip.view;

import javax.swing.*;
import java.awt.*;

/**
 * TipDialog主体面板
 *
 * @author LiuTao
 * @date 2021/1/27
 */
public class TipFeaturePanel extends JPanel {

    public TipFeaturePanel(String context) {
        super(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setBackground(Color.white);

        JTextArea feature = new JTextArea(context);
        JScrollPane jScrollPane = new JScrollPane(feature);

        feature.setEditable(false);
        feature.setForeground(Color.black);
        feature.setFont(new Font("宋体", Font.PLAIN, 13));
        feature.setBackground(Color.white);
        feature.setLineWrap(true);

        jScrollPane.setPreferredSize(new Dimension(260, 100));
        jScrollPane.setBorder(null);
        jScrollPane.setBackground(Color.black);

        JLabel jsp = new JLabel();
        jsp.setPreferredSize(new Dimension(300, 15));

        add(jsp);
        add(jScrollPane);
    }
}
