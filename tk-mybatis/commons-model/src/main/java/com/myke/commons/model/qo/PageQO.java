package com.myke.commons.model.qo;

/**
 * 分页查询对象
 */
public class PageQO<T> {


    /**
     * 当前页号
     */
    private int pageNum = 1;

    /**
     * 一页数量
     */
    private int pageSize = 10;

    /**
     * 排序字段：例：create_time desc,update_time desc
     */
    private String orderBy;

    /**
     * 查询条件实体
     */
    private T condition;

    public PageQO() {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return (this.pageNum - 1) * this.pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }
}