package com.lagou.rabbitmmq.demo;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.UnsupportedEncodingException;

public class ProduceApp {
    public static void main(String[] args) throws UnsupportedEncodingException {

        //AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-rabbit.xml");
        AbstractApplicationContext context=new AnnotationConfigApplicationContext(RabbitConfig.class);


        RabbitTemplate template = context.getBean(RabbitTemplate.class);


        //MessagePropertiesBuilder 的使用
        MessageProperties properties = MessagePropertiesBuilder
                .newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setContentEncoding("gbk")
                .setHeader("myKey", "myValue")
                .build();


        for (int i = 0; i < 1000; i++) {

            //MessageBuilder 的使用
            Message message = MessageBuilder
                    .withBody(("你好商家啊" + i).getBytes("gbk"))
                    .andProperties(properties)
                    .build();

            template.send("ex.anno.fanout","key.anno",message);
        }


    }
}
