package com.lagou.rabbit.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.rabbit.demo.po.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
