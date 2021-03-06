package com.myke;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myke.demo.entity.User;
import com.myke.demo.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testSequence() {
        User u = new User();
        u.setName("Tomcat");
        u.setAge(18);
        u.setEmail("kellylake@qq.com");
        userMapper.insert(u);

        Long id1 = u.getId();
        u = new User();
        u.setName("Tomcat2");
        userMapper.insert(u);
        Assert.assertEquals("id should increase 1", id1 + 1, u.getId().longValue());
    }

    @Test
    public void testCustomizedSql() {
        System.out.println("maxAge=" + userMapper.selectMaxAge());
    }

    @Test
    public void testPagination() {
        Page<User> page = new Page<>(1, 3);
        userMapper.selectPage(page, null);
        Assert.assertTrue("total should not be 0", page.getTotal() != 0);
        for (User u : page.getRecords()) {
            System.out.println(u);
        }
        Assert.assertEquals("pagination should be 3 per page", 3, page.getRecords().size());
    }

}
