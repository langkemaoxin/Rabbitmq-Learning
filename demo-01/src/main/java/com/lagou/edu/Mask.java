package com.lagou.edu;

public class Mask {
    private Integer id;
    private String type;

    public Mask(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Mask{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
