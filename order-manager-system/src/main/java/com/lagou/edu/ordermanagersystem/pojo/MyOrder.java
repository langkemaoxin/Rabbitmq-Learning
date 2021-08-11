package com.lagou.edu.ordermanagersystem.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "myorder")
public class MyOrder {

    /**
     * 订单ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="order_id")
    private Integer orderId;

    /**
     * 状态值
     */
    private Integer status;

    /**
     * 金额
     */
    private BigDecimal mount;

    /**
     * 创建时间
     */
    @Column(name="create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name="update_time")
    private Date updateTime;

    @Override
    public String toString() {
        return "MyOrder{" +
                "orderId=" + orderId +
                ", status=" + status +
                ", mount=" + mount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getMount() {
        return mount;
    }

    public void setMount(BigDecimal mount) {
        this.mount = mount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
