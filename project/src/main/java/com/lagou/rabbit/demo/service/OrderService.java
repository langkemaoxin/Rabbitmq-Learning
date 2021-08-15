package com.lagou.rabbit.demo.service;

import com.lagou.rabbit.demo.po.Order;

public interface OrderService {
    boolean saveOrder(Order order);

    boolean payOrder(Order order);

    boolean cancelOrder(Order order);
}
