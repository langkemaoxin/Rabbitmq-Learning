package com.lagou.edu.ordermanagersystem.service;

import com.lagou.edu.ordermanagersystem.pojo.MyOrder;
import com.lagou.edu.ordermanagersystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Page<MyOrder> getOrder(Integer pageIndex, Integer pageSize) {
        PageRequest request = PageRequest.of(pageIndex, pageSize);
        Page<MyOrder> all = orderRepository.findAll(request);
        return all;
    }

    public List<MyOrder> getAllOrder() {
        List<MyOrder> list = orderRepository.findAll();
        return list;
    }


    public MyOrder save(MyOrder myOrder) {
        orderRepository.save(myOrder);
        return myOrder;
    }


}
