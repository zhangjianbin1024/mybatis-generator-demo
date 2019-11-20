package com.myke.tk.tkdemo.entity;

import tk.mybatis.mapper.annotation.Version;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_user_info")
public class UserInfo implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 第一名称
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 订单价格
     */
    @Column(name = "orderPrice")
    private BigDecimal orderprice;

    /**
     * 管理者
     */
    private Integer mgr;

    /**
     * 逻辑删除
     */
    private Integer status;

    /**
     * 版本
     */
    @Version
    private Long version;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建人
     */
    @Column(name = "create_option")
    private String createOption;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取名字
     *
     * @return name - 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取第一名称
     *
     * @return first_name - 第一名称
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 设置第一名称
     *
     * @param firstName 第一名称
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取订单价格
     *
     * @return orderPrice - 订单价格
     */
    public BigDecimal getOrderprice() {
        return orderprice;
    }

    /**
     * 设置订单价格
     *
     * @param orderprice 订单价格
     */
    public void setOrderprice(BigDecimal orderprice) {
        this.orderprice = orderprice;
    }

    /**
     * 获取管理者
     *
     * @return mgr - 管理者
     */
    public Integer getMgr() {
        return mgr;
    }

    /**
     * 设置管理者
     *
     * @param mgr 管理者
     */
    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    /**
     * 获取逻辑删除
     *
     * @return status - 逻辑删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置逻辑删除
     *
     * @param status 逻辑删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取版本
     *
     * @return version - 版本
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 设置版本
     *
     * @param version 版本
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取创建人
     *
     * @return create_option - 创建人
     */
    public String getCreateOption() {
        return createOption;
    }

    /**
     * 设置创建人
     *
     * @param createOption 创建人
     */
    public void setCreateOption(String createOption) {
        this.createOption = createOption;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", address=").append(address);
        sb.append(", firstName=").append(firstName);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderprice=").append(orderprice);
        sb.append(", mgr=").append(mgr);
        sb.append(", status=").append(status);
        sb.append(", version=").append(version);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createOption=").append(createOption);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}