package com.css.tzi.imsgserver.message.common;

import com.css.tzi.imsgserver.message.service.MsgHandleDelegate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

/**
 * 订阅消息配置
 *
 * @author LiuTao
 * @date 2021/2/8
 */
@Configuration
public class RedisMessageConfig {
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory
            , MessageListenerAdapter broadAdapter, MessageListenerAdapter postAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(broadAdapter, new ChannelTopic(MsgConstant.TOPIC_MSG_BROAD));
        container.addMessageListener(postAdapter, new ChannelTopic(MsgConstant.TOPIC_MSG_POST));
        return container;
    }

    @Bean
    public MessageListenerAdapter broadAdapter(MsgHandleDelegate msgHandleDelegate) {
        MessageListenerAdapter broadAdapter = new MessageListenerAdapter(msgHandleDelegate);
        broadAdapter.setDefaultListenerMethod("broadMessage");
        broadAdapter.setSerializer(new GenericJackson2JsonRedisSerializer());
        return broadAdapter;
    }

    @Bean
    public MessageListenerAdapter postAdapter(MsgHandleDelegate msgHandleDelegate) {
        MessageListenerAdapter postAdapter = new MessageListenerAdapter(msgHandleDelegate);
        postAdapter.setDefaultListenerMethod("postMessage");
        postAdapter.setSerializer(new GenericJackson2JsonRedisSerializer());
        return postAdapter;
    }
}
