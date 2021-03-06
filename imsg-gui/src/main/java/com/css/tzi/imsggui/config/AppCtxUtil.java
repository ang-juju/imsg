package com.css.tzi.imsggui.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring context工具
 *
 * @author LiuTao
 * @date 2021/1/29
 */
@Component
public class AppCtxUtil implements ApplicationContextAware {
    private static ApplicationContext ctx;

    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        return ctx.getBean(requiredType);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }


}
