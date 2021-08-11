package com.lagou.edu.ordermanagersystem.repository;

import com.lagou.edu.ordermanagersystem.pojo.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<MyOrder,Integer> {
}
