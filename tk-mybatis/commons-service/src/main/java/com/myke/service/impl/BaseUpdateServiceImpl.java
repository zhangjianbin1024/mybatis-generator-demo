package com.myke.service.impl;

import com.myke.service.UpdateService;
import com.myke.tk.base.CrudMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;

public abstract class BaseUpdateServiceImpl<E> implements UpdateService<E> {
    /**
     * 泛型注入
     */
    @Autowired
    private CrudMapper<E> crudMapper;

    protected Class<E> poType;

    public BaseUpdateServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        poType = (Class<E>) pt.getActualTypeArguments()[0];
    }

    @Override
    public int updateByPrimaryKey(E record) {
        Assert.notNull(record, "record is not null");
        int result = crudMapper.updateByPrimaryKey(record);
        if (result == 0) {
            throw new RuntimeException("updateByPrimaryKey 失败!");
        }
        return result;
    }

    @Override
    public int updateByPrimaryKeySelective(E record) {
        Assert.notNull(record, "record is not null");
        int result = crudMapper.updateByPrimaryKeySelective(record);
        if (result == 0) {
            throw new RuntimeException("updateByPrimaryKeySelective 失败!");
        }
        return result;
    }

    @Override
    public int updateByCondition(E record, Example condition) {
        Assert.notNull(record, "record is not null");
        Assert.notNull(condition, "condition is not null");
        int result = crudMapper.updateByCondition(record, condition);
        if (result == 0) {
            throw new RuntimeException("updateByCondition 失败!");
        }
        return result;
    }

    @Override
    public int updateByConditionSelective(E record, Example condition) {
        Assert.notNull(record, "record is not null");
        Assert.notNull(condition, "condition is not null");
        int result = crudMapper.updateByConditionSelective(record, condition);
        if (result == 0) {
            throw new RuntimeException("updateByConditionSelective 失败!");
        }
        return result;
    }

    @Override
    public int updateByExample(E record, Example condition) {
        Assert.notNull(record, "record is not null");
        Assert.notNull(condition, "condition is not null");
        int result = crudMapper.updateByExample(record, condition);
        if (result == 0) {
            throw new RuntimeException("updateByExample 失败!");
        }
        return result;
    }

    @Override
    public int updateByExampleSelective(E record, Example condition) {
        Assert.notNull(record, "record is not null");
        Assert.notNull(condition, "condition is not null");
        int result = crudMapper.updateByExampleSelective(record, condition);
        if (result == 0) {
            throw new RuntimeException("updateByExampleSelective 失败!");
        }
        return result;
    }
}
