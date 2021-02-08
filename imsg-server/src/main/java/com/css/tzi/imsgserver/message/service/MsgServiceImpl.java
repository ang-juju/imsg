package com.css.tzi.imsgserver.message.service;

import com.css.tzi.imsgserver.message.common.MsgConstant;
import com.css.tzi.imsgserver.message.entity.MsgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 消息发送实现
 *
 * @author LiuTao
 * @date 2021/2/8
 */
@Service
public class MsgServiceImpl implements MsgService {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void post(MsgEntity msgEntity) {
        redisTemplate.convertAndSend(MsgConstant.TOPIC_MSG_POST, msgEntity);
    }

    @Override
    public void broad(MsgEntity msgEntity) {
        redisTemplate.convertAndSend(MsgConstant.TOPIC_MSG_BROAD, msgEntity);
    }
}
