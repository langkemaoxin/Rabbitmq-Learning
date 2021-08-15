package com.lagou.rabbit.demo.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {
    CREATE(0, "待支付"),
    PAYED(10, "已支付"),
    CANCEL(30, "已取消");
    private static final Map<Integer, OrderStatus> lookup = new HashMap();

    static {
        for (OrderStatus w : EnumSet.allOf(OrderStatus.class))
            lookup.put(w.getCode(), w);

    }

    public static OrderStatus get(int code) {
        return lookup.get(code);
    }

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
