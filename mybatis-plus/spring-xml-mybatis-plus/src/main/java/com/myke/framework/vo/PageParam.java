package com.myke.framework.vo;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 分页参数,数据需要分页时，xxxDTO 继承该类
 */
public class PageParam {
    @NotNull(message = "当前页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    private Integer pageNo;

    @Range(min = 1, max = 100, message = "条数范围为 [1, 100]")
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public PageParam setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public PageParam setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public final int getOffset() {
        return (pageNo - 1) * pageSize;
    }
}
