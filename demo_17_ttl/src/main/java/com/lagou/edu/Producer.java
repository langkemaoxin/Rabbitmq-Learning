package com.lagou.edu;

import com.rabbitmq.client.*;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Producer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");


        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        Map<String, Object> argments = new HashMap<>();

        //消息的过期时间
        argments.put("x-message-ttl", 10 * 1000);

        //队列如果没有消费者，则会销毁
        argments.put("x-expires", 60 * 1000);

        //参数是针对队列而言的
        channel.queueDeclare("queue.ttl.watting",
                true
                , false
                , false
                , argments);

        channel.exchangeDeclare("ex.ttl.watting"
                , BuiltinExchangeType.DIRECT
                , false
                , false
                , null);

        //把交换机和队列绑定起来
        channel.queueBind("queue.ttl.watting","ex.ttl.watting","key.ttl.watting");

        final AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .contentEncoding("utf-8")
                .deliveryMode(2)
                .build();

        channel.basicPublish("ex.ttl.watting"
        ,"key.ttl.watting"
        ,properties
        ,"等待的订单号".getBytes());

    }
}
