package com.lagou.rabbitmq.demo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeoutException;

public class PublisherConfirmsProducer3 {
    public static void main(String[] args) throws Exception, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");

        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        final AMQP.Confirm.SelectOk selectOk = channel.confirmSelect();

        channel.queueDeclare("queue.pc", true, false, false, null);

        channel.exchangeDeclare("ex.pc", BuiltinExchangeType.DIRECT, true, false, null);

        channel.queueBind("queue.pc", "ex.pc", "key.pc");


        final ConcurrentNavigableMap<Long, String> waitForConfirmMap = new ConcurrentSkipListMap<>();


        ConfirmCallback confirmCallback = new ConfirmCallback() {
            /**
             *
             * @param deliveryTag 返回确认的编号
             * @param multiple 是否是批量确认
             * @throws IOException
             */
            @Override
            public void handle(long deliveryTag, boolean multiple) throws IOException {

                //批量确认
                if (multiple) {
                    System.out.println("编号小于等于" + deliveryTag + "的消息都全部确认");

                    //取出小于deliveryTag的数据
                    ConcurrentNavigableMap<Long, String> headMap = waitForConfirmMap.headMap(deliveryTag, true);

                    //然后清空
                    headMap.clear();
                } else {
                    //确认单个
                    waitForConfirmMap.remove(deliveryTag);
                    System.out.println("编号为："+deliveryTag+"的消息被确认");
                }
            }
        };

        ConfirmCallback nackConfirmCallBack=new ConfirmCallback() {
            @Override
            public void handle(long deliveryTag, boolean multiple) throws IOException {

                if(multiple){
                    System.out.println("小于"+deliveryTag+"的消息不确认");
                }else{
                    System.out.println("编号为：" + deliveryTag + " 的消息不确认");
                }
            }
        };

        //设置监听器,处理确认，不确认的消息
        channel.addConfirmListener(confirmCallback,nackConfirmCallBack);

        String message="hello world";

        for (int i = 0; i < 103; i++) {
            //从通道中，获取下一个即将发送消息的ID
            long nextPublishSeqNo = channel.getNextPublishSeqNo();

            //发送消息
            channel.basicPublish("ex.pc","key.pc",null,(message+i).getBytes());

            System.out.println("编号为："+nextPublishSeqNo+"的消息已经发送，但是尚未确认");

            waitForConfirmMap.put(nextPublishSeqNo,(message+i));

        }

        Thread.sleep(1000);


        channel.close();
        connection.close();

    }
}
