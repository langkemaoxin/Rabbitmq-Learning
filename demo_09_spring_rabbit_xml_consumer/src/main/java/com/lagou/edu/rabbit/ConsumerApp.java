package com.lagou.edu.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerApp {
    public static void main(String[] args) throws Exception {

        AbstractApplicationContext context=new ClassPathXmlApplicationContext("spring-rabbit.xml");

        RabbitTemplate template = context.getBean(RabbitTemplate.class);

        //使用模板从队列中取出一条
        Message receive = template.receive("queue.q1");

        System.out.println(new String(receive.getBody(),receive.getMessageProperties().getContentEncoding()));

        context.close();
    }
}
