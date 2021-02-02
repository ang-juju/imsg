package com.css.imsggui.gui.util;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 图像工厂
 * 扫描resources/images下面的所有图片
 *
 * @author LiuTao
 * @date 2021/2/1
 */
@Slf4j
public final class ImageUtil {
    /**
     * 图像存储器
     */
    private static final Map<String, Image> imageMap;

    static {
        imageMap = new HashMap<>();
        String imageDirectoryPath = ImageUtil.class.getResource("/images/").getPath();
        File imageDirectory = new File(imageDirectoryPath);
        File[] imageFiles = imageDirectory.listFiles();
        if (imageFiles != null) {
            for (File imageFile : imageFiles) {
                String imageFileName = imageFile.getName();
                try {
                    imageMap.put(imageFileName.substring(0, imageFileName.lastIndexOf('.'))
                            , ImageIO.read(imageFile));
                } catch (IOException e) {
                    log.error("图片加载出错", e);
                }
            }
        }
    }

    private ImageUtil() {
    }

    public static Image getImage(String name) {
        return imageMap.get(name);
    }
}
