package mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SqlSessionFactionTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(SqlSessionFactionTest.class);

    private SqlSession sqlS;

    @Before
    public void init() throws IOException {
        System.out.println("mybatis 初始化");
        //读取配置文件内容
        String resource = "mybatis-config.xml";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory sqlSF = new SqlSessionFactoryBuilder().build(inputStream);
        //创建SqlSession
        sqlS = sqlSF.openSession();
    }

    @After
    public void close() {
        System.out.println("mybatis 销毁");
        sqlS.close();
    }

    @Test
    public void selectByExample() {
        List<Object> selectByExample = sqlS.selectList("selectByExample");
        LOGGER.info("selectByExample:{}", selectByExample);
    }

    @Test
    public void selectByExampleByPageHelper() {
        PageHelper.startPage(-1, 10);
        List<Object> selectByExample = sqlS.selectList("selectByExample");
        PageInfo<Object> pageInfo = new PageInfo<>(selectByExample);
        LOGGER.info("selectByExample:{}", selectByExample);
        LOGGER.info("pageInfo:{}", pageInfo);
    }

    @Test
    public void selectLimit() {
        Integer total = null;
        Integer size = 20;
        Integer pages = total % size == 0 ? total / size : total / size + 1;

        List<Object> finalRes = new ArrayList<>(total);
        for (int i = 0; i < pages; i++) {
            Integer toIndex = (i + 1) * size > total ? total : (i + 1) * size;
            //分批查询

        }

    }

}
