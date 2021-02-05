package com.css.tzu.imsgserver.authentication;

import com.css.tzu.imsgserver.common.UserInfo;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 鉴权服务实现
 *
 * @author LiuTao
 * @date 2021/1/25
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public UserInfo doAuth() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(UUID.randomUUID().toString());
        userInfo.setRealName("测试");
        return userInfo;
    }

    @Override
    public Boolean logout() {
        return true;
    }
}
