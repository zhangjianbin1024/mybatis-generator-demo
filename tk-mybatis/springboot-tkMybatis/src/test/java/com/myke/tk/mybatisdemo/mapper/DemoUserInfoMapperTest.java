package com.myke.tk.mybatisdemo.mapper;

import com.myke.tk.mybatisdemo.entiry.DemoUserInfo;
import com.myke.tk.mybatisdemo.entiry.DemoUserInfoExample;
import com.myke.tk.tkdemo.BaseApplication;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 原生 mybatis 测试
 */
public class DemoUserInfoMapperTest extends BaseApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoUserInfoMapperTest.class);

    @Autowired
    private DemoUserInfoMapper userInfoMapper;

    @Test
    public void select() {

        DemoUserInfoExample demoUserInfoExample = new DemoUserInfoExample();
        demoUserInfoExample.createCriteria().andIdEqualTo(3);
        List<DemoUserInfo> demoUserInfos = userInfoMapper.selectByExample(demoUserInfoExample);
        LOGGER.info("demoUserInfos:{}", demoUserInfos);

    }


}