package com.lagou.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class HelloConsumer {
    public static void main(String[] args) throws  Exception, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        //直接从队列里面去拿消息
        GetResponse getResponse = channel.basicGet("queue.biz", true);

        //消息都是二进制数据
        byte[] body = getResponse.getBody();

        System.out.println(new String(body));

        channel.close();

        connection.close();
    }
}
