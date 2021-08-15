package com.lagou.rabbit.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lagou.rabbit.demo.enums.OrderStatus;
import com.lagou.rabbit.demo.mapper.OrderMapper;
import com.lagou.rabbit.demo.po.Order;
import com.lagou.rabbit.demo.service.OrderService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    @Transactional
    public boolean saveOrder(Order order) throws UnsupportedEncodingException {

        //order.setId(1L);

        order.setOrderNo(UUID.randomUUID().toString());
        Date date = new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);
        order.setStatus(OrderStatus.CREATE.getCode());


        final int insert = orderMapper.insert(order);

        boolean flag = insert == 1;

        if (flag) {

            //用于异步更新库存
            /**
             * 将对象转换成Json串
             *
             *  使用的就是 Jackson2JsonMessageConverter
             *  @Bean
             *     public MessageConverter messageConverter(){
             *         return new Jackson2JsonMessageConverter();
             *     }
             */
            rabbitTemplate.convertAndSend("e.biz", "r.biz", order.getCourseId() + "");

            //向TTL队列发送消息,延迟队列
            //rabbitTemplate.send("e.ttl", "r.ttl", new Message(order.getOrderNo().getBytes("utf-8"), MessagePropertiesBuilder.newInstance().build()));
            rabbitTemplate.convertAndSend("e.ttl", "r.ttl", order.getOrderNo());

        }

        return flag;
    }

    @Override
    public boolean payOrder(Order order) {
        int update = orderMapper.update(null, new UpdateWrapper<Order>()
                .set("status", OrderStatus.PAYED.getCode())
                .set("update_time", new Date())
                .eq("order_no", order.getOrderNo())
        );

        return update == 1;
    }

    /**
     * 判断订单是否已经支付，如果没有支付，则取消
     *
     * @param order
     * @return
     */
    @Transactional
    @Override
    public boolean cancelOrder(Order order) {

        final Order orderDb = orderMapper.selectOne(new QueryWrapper<Order>()
                .eq("order_no", order.getOrderNo()));

        //取消订单的功能为 待支付的订单
        if (orderDb == null || orderDb.getStatus() != OrderStatus.CREATE.getCode()) {
            System.out.println("无需更新");
            return false;
        }

        // mapper更新时，传入 UpdateWrapper
        int update = orderMapper.update(null, new UpdateWrapper<Order>()
                .set("status", OrderStatus.CANCEL.getCode())
                .set("update_time", new Date())
                .eq("order_no", order.getOrderNo()));

        return update == 1;
    }

    @Override
    public List<Order> listOrder() {
        List<Order> orders = orderMapper.selectList(new QueryWrapper<>());

        for (Order order : orders) {
            String name = OrderStatus.get(order.getStatus()).getName();
            if (StringUtils.isNotBlank(name))
                order.setStatusName(name);
        }

        return orders;
    }
}
