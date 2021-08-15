package com.lagou.rabbit.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.rabbit.demo.po.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper extends BaseMapper<Order> {
}
