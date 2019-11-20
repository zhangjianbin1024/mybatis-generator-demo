package com.secoo.ccs.n.pay.bean.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_ccs_open_platform")
public class CcsOpenPlatform implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 海关发起请求时，平台接收的会话ID
     */
    @Column(name = "sessionID")
    private String sessionid;

    /**
     * 交易平台向海关申报订单的的订单编号。需返回原始请求内的所有订单。
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 海关系统调用接口时的系统时间
     */
    @Column(name = "serviceTime")
    private Long servicetime;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 版本号
     */
    private Long version;

    /**
     * 0:未上传 1:已上传
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取海关发起请求时，平台接收的会话ID
     *
     * @return sessionID - 海关发起请求时，平台接收的会话ID
     */
    public String getSessionid() {
        return sessionid;
    }

    /**
     * 设置海关发起请求时，平台接收的会话ID
     *
     * @param sessionid 海关发起请求时，平台接收的会话ID
     */
    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    /**
     * 获取交易平台向海关申报订单的的订单编号。需返回原始请求内的所有订单。
     *
     * @return order_no - 交易平台向海关申报订单的的订单编号。需返回原始请求内的所有订单。
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置交易平台向海关申报订单的的订单编号。需返回原始请求内的所有订单。
     *
     * @param orderNo 交易平台向海关申报订单的的订单编号。需返回原始请求内的所有订单。
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取海关系统调用接口时的系统时间
     *
     * @return serviceTime - 海关系统调用接口时的系统时间
     */
    public Long getServicetime() {
        return servicetime;
    }

    /**
     * 设置海关系统调用接口时的系统时间
     *
     * @param servicetime 海关系统调用接口时的系统时间
     */
    public void setServicetime(Long servicetime) {
        this.servicetime = servicetime;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取修改时间
     *
     * @return update_date - 修改时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置修改时间
     *
     * @param updateDate 修改时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取版本号
     *
     * @return version - 版本号
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 设置版本号
     *
     * @param version 版本号
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 获取0:未上传 1:已上传
     *
     * @return status - 0:未上传 1:已上传
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0:未上传 1:已上传
     *
     * @param status 0:未上传 1:已上传
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sessionid=").append(sessionid);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", servicetime=").append(servicetime);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", version=").append(version);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}