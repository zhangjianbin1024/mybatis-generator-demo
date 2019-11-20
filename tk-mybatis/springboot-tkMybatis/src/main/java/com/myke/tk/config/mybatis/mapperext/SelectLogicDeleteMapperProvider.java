package com.myke.tk.config.mybatis.mapperext;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

public class SelectLogicDeleteMapperProvider extends MapperTemplate {
    public SelectLogicDeleteMapperProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String selectAllLogicDelete(MappedStatement ms) {
        //获取了当前接口的实体类型
        final Class<?> entityClass = getEntityClass(ms);
        //修改返回值类型为实体类型
        setResultType(ms, entityClass);

        //纯粹的拼接 XML 形式的 SQL 了。
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectAllColumns(entityClass));
        //from tablename - 支持动态表名
        sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
        // where
        sql.append("<where>");
        //AND `status` = 0
        sql.append(SqlHelper.whereLogicDelete(entityClass, true));
        //order by xxx
        sql.append(SqlHelper.orderByDefault(entityClass));

        sql.append("</where>");
        return sql.toString();
    }

}
