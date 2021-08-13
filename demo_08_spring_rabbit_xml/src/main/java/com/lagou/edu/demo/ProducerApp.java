package com.lagou.edu.demo;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ProducerApp {
    public static void main(String[] args) throws Exception {

        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-rabbit.xml");

        //通过上下文获取 模板
        RabbitTemplate template = context.getBean(RabbitTemplate.class);

        Message message;

        //使用静态方法，创建一个实例
        final MessagePropertiesBuilder builder = MessagePropertiesBuilder.newInstance();

        builder.setContentEncoding("gbk");
        builder.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);

        for (int i = 0; i < 1000; i++) {
            message = MessageBuilder.withBody(("你好世界" + i).getBytes("gbk"))
                    .andProperties(builder.build())
                    .build();

            template.send("ex.direct","routing.q1",message);

        }

        context.close();

    }
}
