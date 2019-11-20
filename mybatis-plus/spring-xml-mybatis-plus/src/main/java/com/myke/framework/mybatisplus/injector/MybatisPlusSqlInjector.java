package com.myke.framework.mybatisplus.injector;


import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.additional.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.injector.methods.additional.LogicDeleteByIdWithFill;
import com.myke.framework.mybatisplus.methods.InsertAllColumn;
import com.myke.framework.mybatisplus.methods.InsertBatchAllColumn;
import com.myke.framework.mybatisplus.methods.UpdateAllColumnById;

import java.util.List;


/**
 * 自定义  MybatisPlusSql注入器
 */
public class MybatisPlusSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        //增加自定义方法
        methodList.add(new InsertAllColumn());
        methodList.add(new InsertBatchAllColumn());
        /**
         * 配置项示例：
         *     //表示sql语句中不要逻辑删除字段
         *                 t -> !t.isLogicDelete()
         *                         //表示sql语句中不要字段名为 version 的字段
         *                         && !t.getProperty().equals("version")
         *                         //表示sql语句中不要字段名为 id 的字段
         *                         && !t.getProperty().equals("id"))
         */
        methodList.add(new InsertBatchSomeColumn(tableFieldInfo -> true));
        //根据 id 逻辑删除数据,并带字段填充功能
        methodList.add(new LogicDeleteByIdWithFill());
        //根据 ID 更新所有字段
        methodList.add(new UpdateAllColumnById());
        return methodList;
    }

}
