package com.css.imsgserver.authentication;

import com.css.imsgserver.common.UserInfo;

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
