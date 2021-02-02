package com.css.imsggui.gui.tip.action;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 点击调起默认浏览器事件监听器
 *
 * @author LiuTao
 * @date 2021/1/28
 */
@Slf4j
public class ClickToBrowseMouseListener extends MouseAdapter {
    private static final Desktop desktop = Desktop.getDesktop();
    private final String url;

    public ClickToBrowseMouseListener(String url) {
        this.url = url;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            desktop.browse(new URI(url));
        } catch (IOException | URISyntaxException ex) {
            log.error("调用浏览器失败", ex);
        }
    }
}
