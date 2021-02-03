package com.css.tzu.imsgserver.authentication;

import com.css.tzu.imsgserver.common.Result;
import com.css.tzu.imsgserver.common.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 鉴权接口
 *
 * @author LiuTao
 * @date 2021/1/24
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Result login() {
        UserInfo userInfo = authService.doAuth();
        return Result.ok(userInfo);
    }

    @PostMapping("/logout")
    public Result logout() {
        authService.logout();
        return Result.ok();
    }
}
