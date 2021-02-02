package com.css.imsgserver.message;

import com.css.imsgserver.common.Result;
import com.css.imsgserver.websocket.WebSocketEndpoint;
import org.springframework.web.bind.annotation.*;

/**
 * 消息分发
 *
 * @author LiuTao
 * @date 2021/1/25
 */
@RestController
@RequestMapping("/msg")
public class MsgController {

    @PostMapping("/broad")
    public Result broad(@RequestBody String msgEntity) {
        WebSocketEndpoint.broadMessage(msgEntity);
        return Result.ok();
    }

    @PostMapping("/{userId}")
    public Result single(@PathVariable String userId, @RequestBody String content) {
        WebSocketEndpoint.singleMessage(userId, content);
        return Result.ok();
    }
}
