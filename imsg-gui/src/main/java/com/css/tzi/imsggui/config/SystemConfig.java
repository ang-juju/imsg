package com.css.tzi.imsggui.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 系统全局配置
 *
 * @author LiuTao
 * @date 2021/1/29
 */
@Data
@Configuration
@PropertySource("classpath:app.properties")
public class SystemConfig {
    @Value("${server.ip}")
    private String serverIp;
    @Value("${server.port}")
    private String serverPort;
    @Value("${server.login.path}")
    private String loginPath;
    @Value("${server.websocket.path}")
    private String websocketPath;

    public String getWebsocketUrl() {
        return "ws://" +
                serverIp +
                ":" +
                serverPort +
                websocketPath;
    }

    public String getRestfulUrl() {
        return "http://" +
                serverIp +
                ":" +
                serverPort;
    }
}
