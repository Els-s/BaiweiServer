package com.els.baiwei.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

/**
 * @Despt:WebSocket 配置
 * @Author: Els-s
 * @Time: 2019/11/12 16:04
 */
@Configuration
@EnableWebSocketMessageBroker
public class SocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    /**
     * 功能描述: 配置连接地址
     * @param:[registry]
     * @return:void
     * @Author:Els-s
     * @Date: 2019/11/12 16:08
     */
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/els") //连接地址
                .setAllowedOrigins("*") //设定连接域
                .withSockJS();
    }

    @Override
    /**
     * 功能描述: 配置消息代理
     * @param:[registry]
     * @return:void
     * @Author:Els-s
     * @Date: 2019/11/12 16:13
     */
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //点对点发送使用queue,点对面发用topike
        registry.enableSimpleBroker("/queue");
    }
}
