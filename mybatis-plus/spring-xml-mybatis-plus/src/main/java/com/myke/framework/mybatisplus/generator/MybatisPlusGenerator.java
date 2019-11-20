package com.myke.framework.mybatisplus.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import org.springframework.util.Assert;

/**
 * mybatis plus 代码生成器
 */
public class MybatisPlusGenerator extends SuperGenerator {
    public void generator(String author, String packageName, String... tableNames) {
        Assert.hasText(author, "开发者不能为空");
        Assert.hasText(packageName, "包名不能为空");
        Assert.notEmpty(tableNames, "表名不能为空");

        // 代码生成器
        AutoGenerator mpg = generateByTables(author, packageName, tableNames);
        mpg.execute();
        for (String tableName : tableNames) {
            System.err.println(" TableName【 " + tableName + " 】" + "Generator Success !");
        }
    }
}
