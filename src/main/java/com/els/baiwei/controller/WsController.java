package com.els.baiwei.controller;

import com.els.baiwei.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * @Despt:
 * @Author: Els-s
 * @Time: 2019/11/12 16:15
 */
@Controller
public class WsController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void receive(Principal principal,Message msg){
        msg.setFrom(principal.getName());
//        System.out.println(msg);
        simpMessagingTemplate.convertAndSendToUser(msg.getTo(),"/queue/chat",msg);
    }
}
