package com.laogou.edu.rabbitmq.demo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class ConsumerApp {
    public static void main(String[] args) {
//        AbstractApplicationContext context = new AnnotationConfigApplicationContext(RabbitmqConfig.class);
//        RabbitTemplate template = context.getBean(RabbitTemplate.class);


        //只要加载容器，就会自动加载进来
        new AnnotationConfigApplicationContext(RabbitmqConfig.class);

    }
}
