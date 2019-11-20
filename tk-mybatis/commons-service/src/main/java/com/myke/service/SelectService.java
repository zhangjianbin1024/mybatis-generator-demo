package com.myke.service;

import com.github.pagehelper.PageInfo;
import com.myke.commons.model.qo.PageQO;
import com.myke.commons.model.vo.PageVO;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface SelectService<T> {

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     *
     * @param record
     * @return
     */
    T selectOne(T record);

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param record
     * @return
     */
    List<T> select(T record);

    /**
     * 查询全部结果
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     *
     * @param record
     * @return
     */
    int selectCount(T record);

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param record
     * @return
     */
    T selectByPrimaryKey(T record);

    /**
     * 根据主键字段查询数据是否存在
     *
     * @param record
     * @return
     */
    boolean existsWithPrimaryKey(T record);

    /**
     * 根据主键字符串进行查询，类中只有存在一个带有@Id注解的字段，值以逗号隔开
     *
     * @param ids
     * @return
     */
    List<T> selectByIds(String ids);

    /**
     * 根据Condition条件进行查询
     *
     * @param condition
     * @return
     */
    List<T> selectByCondition(Example condition);

    /**
     * 根据Condition条件进行查询总数
     *
     * @param condition
     * @return
     */
    int selectCountByCondition(Example condition);

    /**
     * 根据Example条件进行查询
     *
     * @param example
     * @return
     */
    List<T> selectByExample(Example example);


    PageInfo<T> selectPage(T t, Integer page, Integer rows);

    PageVO<T> selectPage(PageQO<?> pageQO);
}
