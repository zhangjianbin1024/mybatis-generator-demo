package com.myke.service.impl;

import com.myke.service.InsertService;
import com.myke.tk.base.CrudMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseInsertServiceImpl<E> implements InsertService<E> {
    /**
     * 泛型注入
     */
    @Autowired
    private CrudMapper<E> crudMapper;

    protected Class<E> poType;

    public BaseInsertServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        poType = (Class<E>) pt.getActualTypeArguments()[0];
    }

    @Override
    public int insert(E record) {
        Assert.notNull(record, "record is not null");
        int result = crudMapper.insert(record);
        if (result == 0) {
            throw new RuntimeException("insert 失败!");
        }
        return result;
    }

    @Override
    public int insertSelective(E record) {
        Assert.notNull(record, "record is not null");
        int result = crudMapper.insertSelective(record);
        if (result == 0) {
            throw new RuntimeException("insertSelective 失败!");
        }
        return result;
    }

    @Override
    public int insertList(List<? extends E> recordList) {
        Assert.notNull(recordList, "recordList is not null");
        int result = crudMapper.insertList(recordList);
        if (result == 0) {
            throw new RuntimeException("insertList 失败!");
        }
        return result;
    }

}
