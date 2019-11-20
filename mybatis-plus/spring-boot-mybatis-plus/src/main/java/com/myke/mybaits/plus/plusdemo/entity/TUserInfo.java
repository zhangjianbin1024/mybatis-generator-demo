package com.myke.mybaits.plus.plusdemo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangjianbin
 * @since 2019-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TUserInfo extends Model<TUserInfo> {

    private static final long serialVersionUID = 1L;

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
    private String firstName;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单价格
     */
    @TableField("orderPrice")
    private BigDecimal orderPrice;

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
    private Long version;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private String createOption;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
