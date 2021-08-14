package com.lagou.edu.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class RabbitConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory factory = new CachingConnectionFactory(URI.create("amqp://root:123456@192.168.192.129:5672/%2f"));
        return factory;
    }

    @Bean
    @Autowired
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory){

        RabbitTemplate template = new RabbitTemplate(factory);

        return template;
    }

    @Bean
    @Autowired
    public RabbitAdmin rabbitAdmin(ConnectionFactory factory){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(factory);
        return rabbitAdmin;
    }


    @Bean
    public Queue queue(){
        Queue queue = QueueBuilder.nonDurable("queue.anno").build();
        return queue;
    }


}
