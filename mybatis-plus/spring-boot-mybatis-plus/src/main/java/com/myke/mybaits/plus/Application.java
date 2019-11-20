package com.myke.mybaits.plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan(basePackages =
        {"com.myke.mybaits.plus.manualcode.mapper",
                "com.myke.mybaits.plus.plusdemo.mapper"
        })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //@Bean //必须new 一个RestTemplate并放入spring容器当中,否则启动时报错
    //public RestTemplate restTemplate() {
    //    HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
    //    httpRequestFactory.setConnectionRequestTimeout(30 * 1000);
    //    httpRequestFactory.setConnectTimeout(30 * 3000);
    //    httpRequestFactory.setReadTimeout(30 * 3000);
    //    return new RestTemplate(httpRequestFactory);
    //}

}