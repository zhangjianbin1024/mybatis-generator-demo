package com.myke.mybaits.plus.manualcode.mapper;

import com.myke.mybaits.plus.ApplicationTest;
import com.myke.mybaits.plus.manualcode.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserMapperTest extends ApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        //条件封装器 Wrapper，所以不填写就是无任何条件
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }


}