package com.lagou.rabbit.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.rabbit.demo.po.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMapper extends BaseMapper<Course> {
}
