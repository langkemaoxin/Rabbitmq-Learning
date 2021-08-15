package com.lagou.rabbit.demo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_course_order")
public class Order implements Serializable {

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;
    private String orderNo;
    private Integer userId;
    private Integer courseId;
    private Integer status; //0 已创建 10已支付 20 已完成 30已取消

    @TableField(exist = false)
    private String statusName;
    private Date createTime;
    private Date updateTime;
}
