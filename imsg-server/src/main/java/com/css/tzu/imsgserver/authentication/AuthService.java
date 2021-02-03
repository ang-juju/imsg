package com.css.tzu.imsgserver.authentication;

import com.css.tzu.imsgserver.common.UserInfo;

/**
 * 鉴权service接口
 *
 * @author LiuTao
 * @date 2021/1/25
 */
public interface AuthService {
    UserInfo doAuth();

    Boolean logout();
}
