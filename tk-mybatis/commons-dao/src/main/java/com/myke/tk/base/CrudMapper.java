package com.myke.tk.base;


import com.myke.tk.base.mapper.DeleteMapper;
import com.myke.tk.base.mapper.InsertMapper;
import com.myke.tk.base.mapper.SelectMapper;
import com.myke.tk.base.mapper.UpdateMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 基础增删改查功能mapper
 */
@RegisterMapper //增加该注解后会自动注册该接口到通用 Mapper（不需要配置 mappers 参数指定该接口了）
public interface CrudMapper<T> extends
        InsertMapper<T>,
        DeleteMapper<T>,
        UpdateMapper<T>,
        SelectMapper<T> {


    /********************** 通用 Mapper方法支持的乐观锁 start ***************************/
    default int deleteWithVersion(T T) {
        int result = delete(T);
        if (result == 0) {
            throw new RuntimeException("deleteWithVersion 删除失败!");
        }
        return result;
    }

    default int deleteByPrimaryKeyWithVersion(T T) {
        int result = deleteByPrimaryKey(T);
        if (result == 0) {
            throw new RuntimeException("deleteByPrimaryKeyWithVersion 删除失败!");
        }
        return result;
    }

    default int updateByPrimaryKeyWithVersion(T T) {
        int result = updateByPrimaryKey(T);
        if (result == 0) {
            throw new RuntimeException("updateByPrimaryKeyWithVersion 更新失败!");
        }
        return result;
    }

    default int updateByPrimaryKeySelectiveWithVersion(T T) {
        int result = updateByPrimaryKeySelective(T);
        if (result == 0) {
            throw new RuntimeException("updateByPrimaryKeySelectiveWithVersion 更新失败!");
        }
        return result;
    }

    default int updateByExampleWithVersion(T T, Object example) {
        int result = updateByExample(T, example);
        if (result == 0) {
            throw new RuntimeException("updateByExampleWithVersion 更新失败!");
        }
        return result;
    }

    default int updateByExampleSelectiveWithVersion(T T, Object example) {
        int result = updateByExampleSelective(T, example);
        if (result == 0) {
            throw new RuntimeException("updateByExampleSelectiveWithVersion 更新失败!");
        }
        return result;
    }
    /********************** 通用 Mapper方法支持的乐观锁 end ***************************/
}
