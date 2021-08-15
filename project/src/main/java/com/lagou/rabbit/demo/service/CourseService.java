package com.lagou.rabbit.demo.service;

import com.lagou.rabbit.demo.po.Course;

import java.util.List;

public interface CourseService {

    List<Course> listCourses();

    boolean updateStore(Integer courseId,Long num);
}
