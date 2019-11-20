package com.myke.tk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages ={ "com.myke.tk.tkdemo.mapper","com.myke.tk.mybatisdemo.mapper"}) //不能扫描com.myke.tk.base.CrudMapper 接口包，否则报错
@SpringBootApplication
@ImportResource("classpath:transaction.xml") // 引入xml 配置的事务
@EnableTransactionManagement
public class TkMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(TkMybatisApplication.class, args);
    }
}
