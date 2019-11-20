package com.myke.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myke.commons.model.qo.PageQO;
import com.myke.commons.model.vo.PageVO;
import com.myke.service.CrudService;
import com.myke.tk.base.CrudMapper;
import com.myke.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * MYSQL通用CRUD服务类
 * <p>
 * 1.泛型E 一定要有对应的 mapper 类，对应的 mapper extends CrudMapper 类
 * 2.service实现类需要继承 BaseMySqlCrudServiceImpl
 *
 * @param <E>
 */
public abstract class BaseMySqlCrudServiceImpl<E> implements CrudService<E> {

    /**
     * 使用 spring4.x 泛型注入，
     * 会根据 E 的类型注入相应的mapper接口相应的子类
     */
    @Autowired
    protected CrudMapper<E> crudMapper;


    protected Class<E> poType;

    public BaseMySqlCrudServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        poType = (Class<E>) pt.getActualTypeArguments()[0];
    }


    /************************delete*************************************/
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

    /************************insert*************************************/
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

    /************************select*************************************/
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
                    //其他情况下
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

    /************************update*************************************/
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


    public CrudMapper<E> getCrudMapper() {
        return crudMapper;
    }

    public Class<E> getPoType() {
        return poType;
    }


}
