package com.lagou.rabbit.demo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class User implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 注册手机
     */
    private String phone;

    /**
     * 用户密码
     */
    private String password;
}
