package com.css.tzi.imsggui.gui.tip.view;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

/**
 * 提示窗体
 *
 * @author LiuTao
 * @date 2021/1/27
 */
@Slf4j
public final class TipDialog extends JDialog {
    private static final Dimension dimension;
    private static final Insets screenInsets;

    static {
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(new Frame().getGraphicsConfiguration());
    }

    // 窗体初始隐藏位置x坐标
    private final int hiddenX;
    // 窗体初始隐藏位置y坐标
    private final int hiddenY;

    /**
     * 构造窗体
     *
     * @param width  窗体宽度
     * @param height 窗体高度
     */
    public TipDialog(int width, int height) {
        // 设置窗体大小
        setSize(width, height);
        // 计算隐藏位置x坐标
        hiddenX = (int) (dimension.getWidth() - screenInsets.right - width - 5);
        // 计算隐藏位置y坐标
        hiddenY = (int) (dimension.getHeight() - screenInsets.bottom);
        // 设置初始位置(隐藏)
        setLocation(hiddenX, hiddenY);
        // 设置边框样式,宽度和颜色
        getRootPane().setBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        // 设置背板颜色
        setBackground(Color.yellow);
        // 设置高层显示
        setAlwaysOnTop(true);
        // 设置固定窗体大小
        setResizable(false);
        // 设置不修饰窗体
        setUndecorated(true);
    }

    /**
     * 完全出现
     */
    public void appearWhole() {
        setVisible(true);
        setLocation(hiddenX, hiddenY - getHeight() - 5);
    }

    /**
     * 渐出
     */
    public void appear() {
        setVisible(true);
        int appearY = hiddenY - getHeight() - 5;
        while (getY() > appearY) {
            setLocation(hiddenX, getY() - 1);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                appearWhole();
                return;
            }
        }
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        disappear();
    }

    /**
     * 渐隐
     */
    public void disappear() {
        while (getY() < hiddenY) {
            setLocation(hiddenX, getY() + 1);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                appearWhole();
                return;
            }
        }
        dispose();
    }
}
