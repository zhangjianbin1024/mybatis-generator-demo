package com.myke.service.impl;

import com.myke.service.DeleteService;
import com.myke.tk.base.CrudMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;

public abstract class BaseDeleteServiceImpl<E> implements DeleteService<E> {
    /**
     * 泛型注入
     */
    @Autowired
    private CrudMapper<E> crudMapper;

    protected Class<E> poType;

    public BaseDeleteServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        poType = (Class<E>) pt.getActualTypeArguments()[0];
    }

    @Override
    public int delete(E record) {
        Assert.notNull(record, "record is not null");
        int result = crudMapper.delete(record);
        if (result == 0) {
            throw new RuntimeException("delete 删除失败!");
        }
        return result;
    }

    @Override
    public int deleteByPrimaryKey(E record) {
        Assert.notNull(record, "record is not null");
        int result = crudMapper.deleteByPrimaryKey(record);
        if (result == 0) {
            throw new RuntimeException("deleteByPrimaryKey 删除失败!");
        }
        return result;
    }

    @Override
    public int deleteByCondition(Example condition) {
        Assert.notNull(condition, "condition is not null");
        int result = crudMapper.deleteByCondition(condition);
        if (result == 0) {
            throw new RuntimeException("deleteByCondition 删除失败!");
        }
        return result;
    }

    @Override
    public int deleteByIds(String ids) {
        Assert.notNull(ids, "ids is not null");
        int result = crudMapper.deleteByIds(ids);
        if (result == 0) {
            throw new RuntimeException("deleteByIds 删除失败!");
        }
        return result;
    }


}