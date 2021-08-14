package com.lagou.edu.demo_14_springboot_rabbitmq.controller;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class MessageController {

    /**
     * 这里直接就可以从容器中获取了，
     *
     * 那么，这个template对象，究竟是什么时候进行初始化的呢？
     */
    @Autowired
    private AmqpTemplate template;

    @RequestMapping("rabbit/{message}")
    public String sendMessage(@PathVariable String message) throws UnsupportedEncodingException {

        MessageProperties properties = MessagePropertiesBuilder.newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setContentEncoding("utf-8")
                .setHeader("hello", "world")
                .build();

        Message message1 = MessageBuilder.withBody(message.getBytes("utf-8"))
                .andProperties(properties)
                .build();


        //这里必须写明究竟是使用哪个交换机，bingKey
        template.send("ex.boot","key.boot",message1);
       // template.send(message1);

        return "ok";
    }

}
