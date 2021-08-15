package com.lagou.rabbit.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lagou.rabbit.demo.mapper.CourseMapper;
import com.lagou.rabbit.demo.po.Course;
import com.lagou.rabbit.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> listCourses() {
        List<Course> courses = courseMapper.selectList(new QueryWrapper<>());
        return courses;
    }

    @Override
    @Transactional
    public boolean updateStore(Integer courseId, Long num) {
        System.out.println("执行库存扣减，商品编号"+courseId);
        return false;
    }
}
