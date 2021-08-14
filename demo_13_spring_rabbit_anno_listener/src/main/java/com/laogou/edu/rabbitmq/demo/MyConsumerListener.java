package com.laogou.edu.rabbitmq.demo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MyConsumerListener {

    @RabbitListener(queues = "queue.anno")
    public void whenMessageCome(@Payload String messageStr) {
        System.out.println("我是监听器HI："+messageStr);
    }
}
