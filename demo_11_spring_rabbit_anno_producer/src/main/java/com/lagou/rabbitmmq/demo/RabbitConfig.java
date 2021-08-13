package com.lagou.rabbitmmq.demo;

import org.springframework.amqp.core.*;
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


    /**
     * 定义一个 连接工厂
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setUri("xxx");

        ConnectionFactory factory
                = new CachingConnectionFactory(URI.create("amqp://root:123456@192.168.192.129:5672/%2f"));

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

    /**
     * 使用 Queue构造器，新建一个队列
     * @return
     */
    @Bean
    public Queue queue(){
        Queue queue = QueueBuilder.nonDurable("queue.anno").build();
        return queue;
    }

    @Bean
    public Exchange exchange(){
//        Exchange exchange = ExchangeBuilder.directExchange("ex.anno.fanout").build();
//        return exchange;

        FanoutExchange exchange = new FanoutExchange("ex.anno.fanout", false, false, null);
        return exchange;
    }

    @Bean
    @Autowired
    public Binding binding(Exchange exchange,Queue queue){
        Binding binding = BindingBuilder.bind(queue).to(exchange).with("key.anno").noargs();
        return binding;
    }
}
