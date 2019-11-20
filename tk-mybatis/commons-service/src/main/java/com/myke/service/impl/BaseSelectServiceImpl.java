package com.myke.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myke.commons.model.qo.PageQO;
import com.myke.commons.model.vo.PageVO;
import com.myke.service.SelectService;
import com.myke.tk.base.CrudMapper;
import com.myke.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseSelectServiceImpl<E> implements SelectService<E> {
    /**
     * 泛型注入
     */
    @Autowired
    private CrudMapper<E> crudMapper;

    protected Class<E> poType;

    public BaseSelectServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        poType = (Class<E>) pt.getActualTypeArguments()[0];
    }

    @Override
    public E selectOne(E record) {
        Assert.notNull(record, "record is not null");
        return crudMapper.selectOne(record);
    }

    @Override
    public List<E> select(E record) {
        Assert.notNull(record, "record is not null");
        return crudMapper.select(record);
    }

    @Override
    public List<E> selectAll() {
        return crudMapper.selectAll();
    }

    @Override
    public int selectCount(E record) {
        Assert.notNull(record, "record is not null");
        return crudMapper.selectCount(record);
    }

    @Override
    public E selectByPrimaryKey(E record) {
        Assert.notNull(record, "record is not null");
        return crudMapper.selectByPrimaryKey(record);
    }

    @Override
    public boolean existsWithPrimaryKey(E record) {
        Assert.notNull(record, "record is not null");
        return crudMapper.existsWithPrimaryKey(record);
    }

    @Override
    public List<E> selectByIds(String ids) {
        Assert.notNull(ids, "ids is not null");
        return crudMapper.selectByIds(ids);
    }

    @Override
    public List<E> selectByCondition(Example condition) {
        Assert.notNull(condition, "condition is not null");
        return crudMapper.selectByCondition(condition);
    }

    @Override
    public int selectCountByCondition(Example condition) {
        Assert.notNull(condition, "condition is not null");
        return crudMapper.selectCountByCondition(condition);
    }

    @Override
    public List<E> selectByExample(Example condition) {
        Assert.notNull(condition, "condition is not null");
        return crudMapper.selectByExample(condition);
    }

    @Override
    public PageInfo<E> selectPage(E e, Integer page, Integer rows) {
        // 设置分页参数
        PageHelper.startPage(page, rows);
        //查询
        List<E> list = this.select(e);
        return new PageInfo<E>(list);
    }

    @Override
    public PageVO<E> selectPage(PageQO<?> pageQO) {
        Assert.notNull(pageQO, "pageQO is not null");

        Page<E> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize(), pageQO.getOrderBy());
        try {
            Object condition = pageQO.getCondition();
            if (condition == null) {
                crudMapper.selectAll();
            } else if (condition instanceof Condition) {
                crudMapper.selectByCondition(condition);
            } else if (condition instanceof Example) {
                crudMapper.selectByExample(condition);
            } else if (poType.isInstance(condition)) {
                // Class类的isInstance(Object obj)方法，obj是被测试的对象，如果obj是调用这个方法的class或接口 的实例，则返回true
                // 形象地：自身类.class.isInstance(自身实例或子类实例)  返回true
                crudMapper.select((E) condition);
            } else {
                try {
                    E e = poType.newInstance();
                    BeanUtil.copyProperties(condition, e);
                    crudMapper.select(e);
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException("poType.newInstance occurs InstantiationException or IllegalAccessException", e);
                }
            }
        } finally {
            page.close();
        }

        return PageVO.build(page);
    }
}
