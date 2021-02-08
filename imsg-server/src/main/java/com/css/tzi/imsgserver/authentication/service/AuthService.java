package com.css.tzi.imsgserver.authentication.service;

import com.css.tzi.imsgserver.authentication.entity.UserInfo;

/**
 * 鉴权service接口
 *
 * @author LiuTao
 * @date 2021/1/25
 */
public interface AuthService {
    /**
     * 鉴权
     *
     * @return 用户信息实体
     */
    UserInfo doAuth();

    /**
     * 登出
     */
    void logout();
}
