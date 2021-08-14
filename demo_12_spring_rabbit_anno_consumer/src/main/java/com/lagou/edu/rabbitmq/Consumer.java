package com.lagou.edu.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.UnsupportedEncodingException;

public class Consumer {
    public static void main(String[] args) throws UnsupportedEncodingException {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(RabbitConfig.class);

        final RabbitTemplate template = context.getBean(RabbitTemplate.class);

        //直接用receive方法，指定队列来接收消息
        final Message message = template.receive("queue.anno");

        String content = new String(message.getBody(), message.getMessageProperties().getContentEncoding());

        System.out.println("接收到消息："+content);

    }
}
