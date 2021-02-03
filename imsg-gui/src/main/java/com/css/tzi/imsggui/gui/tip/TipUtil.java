package com.css.tzi.imsggui.gui.tip;

import com.css.tzi.imsggui.gui.tip.action.ClickToBrowseMouseListener;
import com.css.tzi.imsggui.gui.tip.view.TipBottomPanel;
import com.css.tzi.imsggui.gui.tip.view.TipDialog;
import com.css.tzi.imsggui.gui.tip.view.TipFeaturePanel;
import com.css.tzi.imsggui.gui.tip.view.TipHeadPanel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 提示框工具
 *
 * @author LiuTao
 * @date 2021/1/28
 */
@Slf4j
public final class TipUtil {
    /**
     * 私有化构造器
     */
    private TipUtil() {
    }

    public static void show(String title, String content, String url) {
        // 创建提示框窗体
        TipDialog tipDialog = new TipDialog(300, 180);
        // 创建提示框窗体顶部panel
        TipHeadPanel tipHeadPanel = new TipHeadPanel(title);
        // 绑定顶部panel的关闭事件
        tipHeadPanel.getCloseLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tipDialog.disappear();
            }
        });
        // 添加顶部panel到提示窗体
        tipDialog.add(tipHeadPanel, BorderLayout.NORTH);
        // 创建提示框窗体中部panel
        TipFeaturePanel tipFeaturePanel = new TipFeaturePanel(content);
        // 添加中部panel到提示窗体
        tipDialog.add(tipFeaturePanel, BorderLayout.CENTER);
        /* 添加底部panel的浏览器调用跳转 */
        if (StringUtils.hasText(url)) {
            TipBottomPanel tipBottomPanel = new TipBottomPanel();
            tipBottomPanel.getViewButton().addMouseListener(new ClickToBrowseMouseListener(url));
            tipDialog.add(tipBottomPanel, BorderLayout.SOUTH);
        }
        tipDialog.appear();
    }
}
