package com.myke.mybaits.plus.plusdemo.entity;

import java.time.LocalDateTime;
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
 * @since 2019-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderExpress implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDateTime dateTime;

    private String orderId;

    private String expressCode;

    private String updateCode;


}
