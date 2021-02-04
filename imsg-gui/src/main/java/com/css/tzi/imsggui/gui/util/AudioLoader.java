package com.css.tzi.imsggui.gui.util;

import com.sun.media.sound.JavaSoundAudioClip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ResourceUtils;

import java.applet.AudioClip;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 声音工厂
 *
 * @author LiuTao
 * @date 2021/2/3
 */
@Slf4j
public class AudioLoader {
    private static final Map<String, AudioClip> AUDIO_CLIP_MAP;

    static {
        log.debug("开始加载声音资源");
        AUDIO_CLIP_MAP = new HashMap<>();
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources(ResourceUtils.CLASSPATH_URL_PREFIX + "audios/*");
            log.debug("扫描到声音资源{}个", resources.length);
            for (Resource resource : resources) {
                String audioFileName = resource.getFilename();
                if (audioFileName != null) {
                    audioFileName = audioFileName.substring(0, audioFileName.lastIndexOf('.'));
                }
                try (InputStream inputStream = resource.getInputStream()) {
                    AUDIO_CLIP_MAP.put(audioFileName, new JavaSoundAudioClip(inputStream));
                }
            }
        } catch (IOException e) {
            log.error("加载声音资源出错", e);
        }
    }

    private AudioLoader() {
    }

    public static void play(String name) {
        AudioClip audioClip = AUDIO_CLIP_MAP.get(name);
        if (audioClip != null) {
            audioClip.play();
        }
    }
}
