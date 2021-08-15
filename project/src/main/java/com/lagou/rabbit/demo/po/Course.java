package com.lagou.rabbit.demo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("course")
public class Course implements Serializable {

    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 课程一句话简介
     */
    private String brief;

    /**
     * 价格
     */
    private Double price;

    /**
     * 总时长
     */
    private Integer totalDuration;


    /**
     * 课程列表展示图片
     */
    private String courseListImg;

    /**
     * 销量
     */
    private Integer sales;

}
