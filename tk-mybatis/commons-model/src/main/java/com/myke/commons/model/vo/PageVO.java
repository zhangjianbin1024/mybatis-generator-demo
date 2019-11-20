package com.myke.commons.model.vo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.myke.commons.model.qo.PageQO;
import com.myke.util.BeanUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 分页VO对象
 */

public class PageVO<T> {


    /**
     * 当前页号
     */
    private Integer pageNum;


    /**
     * 每页的数量
     */
    private Integer pageSize;

    /**
     * 总记录数
     */
    private Long total;


    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 结果集
     */
    private List<T> list;

    public PageVO() {
    }

    public PageVO(Integer pageNum, Integer pageSize, Long total, Integer pages, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.pages = pages;
        this.list = list;
    }

    public PageVO(PageQO pageQO) {
        this.setPageNum(pageQO.getPageNum());
        this.setPageSize(pageQO.getPageSize());
    }

    public PageVO(List<T> poList) {
        BeanUtil.copyProperties(new PageInfo<>(poList), this);
    }

    public static <T> PageVO<T> build(List<T> poList) {
        return new PageVO<>(poList);
    }

    /**
     * @param page 数据库查出来的分页数据列表
     * @desc 构建一个分页VO对象
     */
    public static <T> PageVO<T> build(Page<T> page) {
        PageVO<T> pageVO = new PageVO<T>();
        BeanUtil.copyProperties(page.toPageInfo(), pageVO);
        return pageVO;
    }

    /**
     * @param page    数据库查出来的分页数据列表
     * @param voClazz 要转为的VO类
     * @desc 构建一个分页VO对象
     * 试用场景为：从数据库取出的PO列表不做任何处理，转化为VO列表返回
     */
    public static <T, E> PageVO<T> build(Page<E> page, Class<T> voClazz) {
        PageVO<T> pageVO = new PageVO<>();
        BeanUtil.copyProperties(page, pageVO, "list");

        try {
            List<T> voList = Lists.newArrayList();
            List<E> poList = page.getResult();
            if (!CollectionUtils.isEmpty(poList)) {
                for (E e : poList) {
                    T t = voClazz.newInstance();
                    BeanUtil.copyProperties(e, t);
                    voList.add(t);
                }
            }
            pageVO.setList(voList);
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }

        return pageVO;
    }

    /**
     * @param poPage 数据库查出来的分页数据
     * @param voList vo数据列表
     * @desc 构建一个分页VO对象
     * 试用场景为：将处理好的VO列表封装返回
     */
    public static <T, E> PageVO<T> build(Page<E> poPage, List<T> voList) {
        PageVO<T> page = new PageVO<>();
        BeanUtil.copyProperties(poPage, page, "list");
        page.setList(voList == null ? Lists.newArrayList() : voList);
        return page;
    }

    /**
     * 计算共计多少页
     *
     * @param total
     * @param pageSize
     * @return
     */
    public static int getPages(long total, int pageSize) {
        if (total == 0 || pageSize == 0) {
            return 0;
        }
        return (int) (total % pageSize == 0 ? (total / pageSize) : (total / pageSize + 1));
    }

    public int getPages() {
        return getPages(this.total, this.pageSize);
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


    @Override
    public String toString() {
        return "PageVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", pages=" + pages +
                ", list=" + list +
                '}';
    }
}
