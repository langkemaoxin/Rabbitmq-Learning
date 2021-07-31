package com.lagou.rabbitmq.demo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.util.concurrent.TimeoutException;

public class PublisherConfirmsProducer2 {
    public static void main(String[] args) throws Exception, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");

        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        final AMQP.Confirm.SelectOk selectOk = channel.confirmSelect();

        channel.queueDeclare("queue.pc", true, false, false, null);

        channel.exchangeDeclare("ex.pc", BuiltinExchangeType.DIRECT, true, false, null);

        channel.queueBind("queue.pc", "ex.pc", "key.pc");

        //批量发送

        String message = "hello-";
        int batchSize = 10;
        int waiteForConfirmCount = 0;

        for (int i = 0; i < 104; i++) {
            channel.basicPublish("ex.pc", "key.pc", null, (message + i).getBytes());
            waiteForConfirmCount++;

            //积攒了一定数量的消息后，主动发起等待消息,这里使用的是同步编程
            if (waiteForConfirmCount == batchSize) {
                channel.waitForConfirmsOrDie(5000);
                System.out.println("消息已经确认");
                waiteForConfirmCount=0;
            }
        }

        if(waiteForConfirmCount>0){
            channel.waitForConfirmsOrDie(5000);
            System.out.println("剩余消息已被确认");
        }


        channel.close();
        connection.close();

    }
}
