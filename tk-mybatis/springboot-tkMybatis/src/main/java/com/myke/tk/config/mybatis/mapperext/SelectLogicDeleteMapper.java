package com.myke.tk.config.mybatis.mapperext;

import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

/**
 * 扩展通用接口
 * <p>
 * 查询逻辑删除的数据
 *
 * @param <T>
 */
@RegisterMapper
public interface SelectLogicDeleteMapper<T> {

    @SelectProvider(type = SelectLogicDeleteMapperProvider.class, method = "dynamicSQL")
    List<T> selectAllLogicDelete();
}
