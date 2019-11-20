package com.myke.service;

import tk.mybatis.mapper.entity.Example;

public interface DeleteService<T> {

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     *
     * @param record
     * @return
     */
    int delete(T record);

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param record
     * @return
     */
    int deleteByPrimaryKey(T record);

    /**
     * 根据Condition条件删除数据
     *
     * @param condition
     * @return
     */
    int deleteByCondition(Example condition);

    /**
     * 根据主键字符串进行删除，类中只有存在一个带有@Id注解的字段
     *
     * @param ids
     * @return
     */
    int deleteByIds(String ids);
}
