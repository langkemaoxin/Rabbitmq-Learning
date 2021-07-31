package com.lagou.rabbitmq.demo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class PublisherConfirmsProducer {
    public static void main(String[] args) throws Exception, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");

        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        final AMQP.Confirm.SelectOk selectOk = channel.confirmSelect();

        channel.queueDeclare("queue.pc",true,false,false,null);

        channel.exchangeDeclare("ex.pc", BuiltinExchangeType.DIRECT,true,false,null);

        channel.queueBind("queue.pc","ex.pc","key.pc");

        //往交换机中发送消息
        channel.basicPublish("ex.pc","key.pc",null,"hello worold".getBytes());

        try{
            channel.waitForConfirmsOrDie(5000);
            System.out.println("发送成功");
        }catch (IOException ex){
            System.out.println("拒收");
        }catch (IllegalStateException ex){
            System.out.println("不是PublishConfirm通道");
        }catch (TimeoutException ex){
            System.out.println("等待消息超时");
        }


        channel.close();
        connection.close();

    }
}
