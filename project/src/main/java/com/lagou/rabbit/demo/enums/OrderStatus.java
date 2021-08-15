package com.lagou.rabbit.demo.enums;

public enum OrderStatus {
    CREATE(0, "待支付"),
    PAYED(10, "已支付"),
    CANCEL(30, "已取消");

    OrderStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private Integer code;
    private String name;
    ;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
