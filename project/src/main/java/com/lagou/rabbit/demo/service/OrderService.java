package com.lagou.rabbit.demo.service;

import com.lagou.rabbit.demo.po.Order;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface OrderService {
    boolean saveOrder(Order order) throws UnsupportedEncodingException;

    boolean payOrder(Order order);

    boolean cancelOrder(Order order);

    List<Order> listOrder();
}
