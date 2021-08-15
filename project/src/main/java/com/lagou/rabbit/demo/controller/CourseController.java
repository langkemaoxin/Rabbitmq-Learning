package com.lagou.rabbit.demo.controller;

import com.lagou.rabbit.demo.po.Course;
import com.lagou.rabbit.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("list")
    public String getCourseList(Model model){
        List<Course> courses = courseService.listCourses();
        model.addAttribute("courses",courses);

        return "courseList";
    }


}
