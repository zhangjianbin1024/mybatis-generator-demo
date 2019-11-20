package com.myke.framework.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果
 */
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = -5902589149129017916L;
    /**
     * 数据
     */
    private List<T> list;

    /**
     * 总量
     */
    private Integer total;

    public List<T> getList() {
        return list;
    }

    public PageResult<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public PageResult<T> setTotal(Integer total) {
        this.total = total;
        return this;
    }

}
