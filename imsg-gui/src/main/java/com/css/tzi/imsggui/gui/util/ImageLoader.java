package com.css.tzi.imsggui.gui.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
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
public final class ImageLoader {
    /**
     * 图像存储器
     */
    private static final Map<String, Image> IMAGE_MAP;

    static {
        log.debug("开始加载图片资源");
        IMAGE_MAP = new HashMap<>();
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources(ResourceUtils.CLASSPATH_URL_PREFIX + "images/*");
            log.debug("扫描到图片资源{}个", resources.length);
            for (Resource resource : resources) {
                String imageFileName = resource.getFilename();
                if (imageFileName != null) {
                    imageFileName = imageFileName.substring(0, imageFileName.lastIndexOf('.'));
                }
                try (InputStream inputStream = resource.getInputStream()) {
                    IMAGE_MAP.put(imageFileName, ImageIO.read(inputStream));
                }
            }
        } catch (IOException e) {
            log.error("加载图片资源出错", e);
        }
    }

    private ImageLoader() {
    }

    public static Image getImage(String name) {
        return IMAGE_MAP.get(name);
    }
}
