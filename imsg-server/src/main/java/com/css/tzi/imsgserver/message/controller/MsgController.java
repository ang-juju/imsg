package com.css.tzi.imsgserver.message.controller;

import com.css.tzi.imsgserver.common.Result;
import com.css.tzi.imsgserver.message.entity.MsgEntity;
import com.css.tzi.imsgserver.message.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送消息接口
 *
 * @author LiuTao
 * @date 2021/1/25
 */
@RestController
@RequestMapping("/msg")
public class MsgController {

    @Autowired
    private MsgService msgService;

    @PostMapping("/post")
    public Result post(@RequestBody MsgEntity msgEntity) {
        msgService.post(msgEntity);
        return Result.ok();
    }

    @PostMapping("/broad")
    public Result broad(@RequestBody MsgEntity msgEntity) {
        msgService.broad(msgEntity);
        return Result.ok();
    }
}
