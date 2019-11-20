package com.myke.tk.tkdemo.mapper;

import com.myke.tk.tkdemo.BaseApplication;
import com.myke.tk.tkdemo.entity.UserInfo;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.internal.matchers.GreaterOrEqual;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 通用 mapper 测试
 * Example功能类似的 Condition查询，仅仅名字不同
 */
public class UserinfoMapperTest extends BaseApplication {


    @Resource
    private UserInfoMapper userInfoMapper;

    //@Ignore
    @Test
    public void init() {
        List<UserInfo> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            UserInfo userInfo = getUserInfo();
            userInfo.setName("zhangjianbin");
            userInfo.setAddress("bj");
            userInfo.setFirstName("zhang");
            userInfo.setOrderId(0L);
            userInfo.setOrderprice(new BigDecimal("0"));
            userInfo.setMgr(0);
            userInfo.setStatus(1);
            userInfo.setVersion(0L);
            userInfo.setCreateTime(new Date());
            userInfo.setUpdateTime(new Date());

            list.add(userInfo);
        }
        userInfoMapper.insertList(list);
    }


    private UserInfo getUserInfo() {
        UserInfo userinfo = new UserInfo();
        return userinfo;
    }


    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     * <p>
     * DELETE FROM `t_user_info` WHERE `id` = ?
     */
    @Test
    public void deleteByPrimaryKey() {
        UserInfo userinfo = getUserInfo();
        userinfo.setId(2);
        int i = userInfoMapper.deleteByPrimaryKey(userinfo);
        Assert.assertEquals(1, i);
    }

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     * <p>
     * DELETE FROM `t_user_info` WHERE `address` = ?
     */
    @Test
    public void delete() {
        UserInfo userinfo = getUserInfo();
        userinfo.setAddress("sh");
        userinfo.setId(44);
        int i = userInfoMapper.delete(userinfo);
        Assert.assertEquals(1, i);

    }

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     */
    //@Transactional //加事务注解，则不保存数据
    @Test
    public void insert() {
        UserInfo userinfo = getUserInfo();
        userinfo.setName("zhangjianbin");
        userinfo.setAddress("bj");
        userinfo.setFirstName("zhang");
        userinfo.setOrderId(0L);
        userinfo.setOrderprice(new BigDecimal("0"));
        userinfo.setMgr(0);
        userinfo.setStatus(1);
        userinfo.setCreateTime(new Date());
        userinfo.setUpdateTime(new Date());

        int i = userInfoMapper.insert(userinfo);
        Assert.assertEquals(1, i);

    }


    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     * <p>
     * INSERT INTO `t_user_info` ( `id`,`name`,`address` ) VALUES( ?,?,? )
     */
    @Test
    public void insertSelective() {
        UserInfo userinfo = getUserInfo();
        userinfo.setName("zhangjianbin");
        userinfo.setAddress("sh");

        int i = userInfoMapper.insertSelective(userinfo);
        Assert.assertEquals(1, i);

    }

    /**
     * 根据主键查询，数据是否存在
     * <p>
     * 根据主键字段查询总数，方法参数必须包含完整的主键属性，查询条件使用等号
     * <p>
     * SELECT CASE WHEN COUNT(`id`) > 0 THEN 1 ELSE 0 END AS result FROM `t_user_info` WHERE `id` = ?
     */
    @Test
    public void existsWithPrimaryKey() {
        UserInfo userinfo = getUserInfo();
        userinfo.setName("zhangjianbin");
        userinfo.setId(2);

        boolean i = userInfoMapper.existsWithPrimaryKey(userinfo);
        Assert.assertTrue(i);

    }

    /**
     * 查询全部结果
     * <p>
     * SELECT `id`,`name`,`address`,`first_name`,`order_id`,`orderPrice`,`mgr`,`status` FROM `t_user_info`
     */
    @Test
    public void selectAll() {
        List<UserInfo> list = userInfoMapper.selectAll();
        Assert.assertNotNull(list);
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     * <p>
     * SELECT `id`,`name`,`address`,`first_name`,`order_id`,`orderPrice`,`mgr`,`status` FROM `t_user_info` WHERE `id` = ?
     */
    @Test
    public void selectByPrimaryKey() {
        UserInfo userinfo = getUserInfo();
        userinfo.setId(17);
        userinfo.setAddress("sh");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userinfo);
        Assert.assertNotNull(userInfo);
    }

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     * <p>
     * SELECT COUNT(`id`) FROM `t_user_info` WHERE `id` = ? AND `address` = ?
     */
    @Test
    public void selectCount() {
        UserInfo userinfo = getUserInfo();
        userinfo.setId(17);
        userinfo.setAddress("sh");
        int i = userInfoMapper.selectCount(userinfo);
        //GreaterOrEqual 匹配符表明如果所测试的数值 i大于等于0 则测试通过
        Assert.assertThat(i, new GreaterOrEqual<>(0));

    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     * <p>
     * SELECT `id`,`name`,`address`,`first_name`,`order_id`,`orderPrice`,`mgr`,`status` FROM `t_user_info` WHERE `id` = ? AND `address` = ?
     */
    @Test
    public void select() {
        UserInfo userinfo = getUserInfo();
        userinfo.setId(17);
        userinfo.setAddress("sh");
        List<UserInfo> list = userInfoMapper.select(userinfo);
        Assert.assertNotNull(list);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     * <p>
     * SELECT `id`,`name`,`address`,`first_name`,`order_id`,`orderPrice`,`mgr`,`status` FROM `t_user_info` WHERE `id` = ? AND `address` = ?
     */
    @Test
    public void selectOne() {
        UserInfo userinfo = getUserInfo();
        userinfo.setId(17);
        userinfo.setAddress("sh");
        UserInfo userInfo = userInfoMapper.selectOne(userinfo);
        Assert.assertNotNull(userInfo);
    }


    /**
     * 根据主键更新实体全部字段，null值会被更新
     * <p>
     * UPDATE `t_user_info` SET `name` = ?,`address` = ?,`first_name` = ?,`order_id` = ?,`orderPrice` = ?,`mgr` = ?,`status` = ? WHERE `id` = ?
     */
    @Test
    public void updateByPrimaryKey() {
        UserInfo userinfo = getUserInfo();
        userinfo.setId(43);
        userinfo.setAddress("hn");
        int i = userInfoMapper.updateByPrimaryKey(userinfo);
        Assert.assertThat(i, new IsEqual<>(1));

    }

    /**
     * 根据主键更新属性不为null的值
     * <p>
     * UPDATE `t_user_info` SET `address` = ? WHERE `id` = ?
     */
    @Test
    public void updateByPrimaryKeySelective() {
        UserInfo userinfo = getUserInfo();
        userinfo.setId(17);
        userinfo.setAddress("hn");
        int i = userInfoMapper.updateByPrimaryKeySelective(userinfo);
        Assert.assertThat(i, new IsEqual<>(1));

    }

    /**
     * 根据Condition条件删除数据
     * <p>
     * DELETE FROM `t_user_info` WHERE ( ( `id` = ? and `name` = ? ) )
     * <p>
     * example  和 weekend 的写法是一致的
     */
    @Test
    public void deleteByCondition() {
        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", "2");
        criteria.andEqualTo("name", "keke");
        int i = userInfoMapper.deleteByCondition(example);
        Assert.assertThat(i, new IsEqual<>(0));

        // 避免字符串形式的字段名
        Weekend<UserInfo> weekend = Weekend.of(UserInfo.class);
        weekend.weekendCriteria()
                .andEqualTo(UserInfo::getId, "2")
                .andEqualTo(UserInfo::getName, "keke");


        i = userInfoMapper.deleteByCondition(weekend);
        Assert.assertThat(i, new IsEqual<>(0));

    }

    /**
     * 根据Condition条件进行查询
     * <p>
     * SELECT `id`,`name`,`address`,`first_name`,`order_id`,`orderPrice`,`mgr`,`status` FROM `t_user_info` WHERE ( ( `id` = ? and `name` = ? ) )
     */
    @Test
    public void selectByCondition() {

        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", "2");
        criteria.andEqualTo("name", "zhangjianbin");
        List<UserInfo> list = userInfoMapper.selectByCondition(example);
        Assert.assertNotNull(list);

        // 避免字符串形式的字段名
        Weekend<UserInfo> weekend = Weekend.of(UserInfo.class);
        weekend.weekendCriteria()
                .andEqualTo(UserInfo::getId, "2")
                .andEqualTo(UserInfo::getName, "zhangjianbin");


        list = userInfoMapper.selectByCondition(weekend);
        Assert.assertNotNull(list);

    }

    /**
     * 根据Condition条件进行查询总数
     * <p>
     * SELECT COUNT(*) FROM `t_user_info` WHERE ( ( `id` = ? and `name` = ? ) )
     */
    @Test
    public void selectCountByCondition() {
        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", "2");
        criteria.andEqualTo("name", "zhangjianbin");
        int i = userInfoMapper.selectCountByCondition(example);
        Assert.assertThat(i, new IsEqual<>(1));

        // 避免字符串形式的字段名
        Weekend<UserInfo> weekend = Weekend.of(UserInfo.class);
        weekend.weekendCriteria()
                .andEqualTo(UserInfo::getId, "2")
                .andEqualTo(UserInfo::getName, "zhangjianbin");


        i = userInfoMapper.selectCountByCondition(weekend);
        Assert.assertThat(i, new IsEqual<>(1));

    }

    /**
     * 根据Condition条件更新实体`record`包含的全部属性，null值会被更新
     * <p>
     * UPDATE `t_user_info` SET `name` = ?,`address` = ?,`first_name` = ?,`order_id` = ?,`orderPrice` = ?,`mgr` = ?,`status` = ? WHERE ( ( `id` = ? and `name` = ? ) )
     */
    @Test
    public void updateByCondition() {
        UserInfo userinfo = getUserInfo();
        userinfo.setAddress("hn");
        userinfo.setName("zhangjianbin");

        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", 17);
        criteria.andEqualTo("name", "zhangjianbin");
        int i = userInfoMapper.updateByCondition(userinfo, example);
        Assert.assertThat(i, new IsEqual<>(1));

        // 避免字符串形式的字段名
        Weekend<UserInfo> weekend = Weekend.of(UserInfo.class);
        weekend.weekendCriteria()
                .andEqualTo(UserInfo::getId, 17)
                .andEqualTo(UserInfo::getName, "zhangjianbin");


        i = userInfoMapper.updateByCondition(userinfo, weekend);
        Assert.assertThat(i, new IsEqual<>(1));

    }

    /**
     * 根据Condition条件更新实体`record`包含的不是null的属性值
     * <p>
     * UPDATE `t_user_info` SET `name` = ?,`address` = ? WHERE ( ( `id` = ? and `name` = ? ) )
     */
    @Test
    public void updateByConditionSelective() {
        UserInfo userinfo = getUserInfo();
        userinfo.setAddress("hn");
        userinfo.setName("zhangjianbin");

        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", 17);
        criteria.andEqualTo("name", "zhangjianbin");
        int i = userInfoMapper.updateByConditionSelective(userinfo, example);
        Assert.assertThat(i, new IsEqual<>(1));

        // 避免字符串形式的字段名
        Weekend<UserInfo> weekend = Weekend.of(UserInfo.class);
        weekend.weekendCriteria()
                .andEqualTo(UserInfo::getId, 17)
                .andEqualTo(UserInfo::getName, "zhangjianbin");


        i = userInfoMapper.updateByConditionSelective(userinfo, weekend);
        Assert.assertThat(i, new IsEqual<>(1));

    }

    /**
     * 根据Example条件进行查询
     * <p>
     * SELECT `id`,`name`,`address`,`first_name`,`order_id`,`orderPrice`,`mgr`,`status` FROM `t_user_info` WHERE ( ( `name` = ? ) )
     */
    @Test
    public void selectByExample() {

        //原始写法
        Example example = new Example(UserInfo.class);
        example.createCriteria().andEqualTo("name", "zhangjianbin");
        List<UserInfo> list = userInfoMapper.selectByExample(example);
        Assert.assertNotNull(list);

        //Sqls 写法
        example = new Example.Builder(UserInfo.class)
                .where(Sqls.custom()
                        .andEqualTo("name", "zhangjianbin"))
                .build();
        list = userInfoMapper.selectByExample(example);
        Assert.assertNotNull(list);

        //WeekendSqls 写法
        example = new Example.Builder(UserInfo.class)
                .where(WeekendSqls.<UserInfo>custom()
                        .andEqualTo(UserInfo::getName, "zhangjianbin"))
                .build();
        list = userInfoMapper.selectByExample(example);
        Assert.assertNotNull(list);

    }

    /**
     * 根据Example条件进行查询
     * <p>
     * 1.Example.Builder 写法
     * 2.排序
     * 3.设置查询列
     * 4.去重
     */
    @Test
    public void selectByExampleOther() {

        //SELECT `id`,`name`,`address`,`first_name`,`order_id`,`orderPrice`,`mgr`,`status` FROM `t_user_info` WHERE ( ( `name` = ? ) ) FOR UPDATE
        //原始写法
        Example example = new Example(UserInfo.class);
        example.setForUpdate(true);
        example.createCriteria().andEqualTo("name", "zhangjianbin");
        List<UserInfo> list = userInfoMapper.selectByExample(example);
        Assert.assertNotNull(list);

        // Example.Builder 写法
        example = new Example.Builder(UserInfo.class)
                .setForUpdate(true)
                .where(WeekendSqls.<UserInfo>custom()
                        .andEqualTo(UserInfo::getName, "zhangjianbin"))
                .build();

        list = userInfoMapper.selectByExample(example);
        Assert.assertNotNull(list);

        // 排序
        //SELECT `id`,`name`,`address`,`first_name`,`order_id`,`orderPrice`,`mgr`,`status` FROM `t_user_info` WHERE ( ( `name` = ? ) ) order by `id` Desc,`name` Asc
        example = new Example.Builder(UserInfo.class)
                .where(WeekendSqls.<UserInfo>custom()
                        .andEqualTo(UserInfo::getName, "zhangjianbin"))
                .orderByDesc("id").orderByAsc("name")
                .build();
        list = userInfoMapper.selectByExample(example);
        Assert.assertNotNull(list);

        //SELECT distinct `name` FROM `t_user_info` WHERE ( ( `name` = ? ) ) order by `name` Desc
        //设置查询列，去重、排序
        example = new Example.Builder(UserInfo.class)
                .select("name")
                .where(WeekendSqls.<UserInfo>custom()
                        .andEqualTo(UserInfo::getName, "zhangjianbin"))
                .orderByDesc("name")
                .distinct()
                .build();
        list = userInfoMapper.selectByExample(example);
        Assert.assertNotNull(list);
    }

    /**
     * 根据Example条件更新实体`record`包含的不是null的属性值
     * <p>
     * UPDATE `t_user_info` SET `name` = ? WHERE ( ( `id` = ? ) )
     */
    @Test
    public void updateByExampleSelective() {
        UserInfo userInfo = getUserInfo();
        userInfo.setName("zhangjianbin");

        Example example = new Example.Builder(UserInfo.class)
                .setForUpdate(true)
                .where(WeekendSqls.<UserInfo>custom()
                        .andEqualTo(UserInfo::getId, "2"))
                .build();
        userInfoMapper.updateByExampleSelective(userInfo, example);

    }

    /**
     * 根据主键字符串进行删除，类中只有存在一个带有@Id注解的字段
     * <p>
     * DELETE FROM `t_user_info` where `id` in (10,20)
     */
    @Test
    public void deleteByIds() {
        String ids = "10,20";
        int i = userInfoMapper.deleteByIds(ids);
        Assert.assertThat(i, new IsEqual<>(0));

    }

    /**
     * 根据主键字符串进行查询，类中只有存在一个带有@Id注解的字段
     * <p>
     * SELECT `id`,`name`,`address`,`first_name`,`order_id`,`orderPrice`,`mgr`,`status` FROM `t_user_info` where `id` in (2,3)
     */
    @Test
    public void selectByIds() {
        String ids = "2,3";
        List<UserInfo> list = userInfoMapper.selectByIds(ids);
        Assert.assertNotNull(list);
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含`id`属性并且必须为自增列
     * <p>
     * INSERT INTO `t_user_info` ( `name`,`address`,`first_name`,`order_id`,`orderPrice`,`mgr`,`status` )
     * VALUES ( ?,?,?,?,?,?,? ) , ( ?,?,?,?,?,?,? ) , ( ?,?,?,?,?,?,? ) , ( ?,?,?,?,?,?,? ) , ( ?,?,?,?,?,?,? )
     */
    @Test
    public void insertList() {
        List<UserInfo> list = new ArrayList<UserInfo>();

        for (int i = 0; i <= 4; i++) {
            UserInfo userInfo = getUserInfo();
            userInfo.setName("keke" + i);
            userInfo.setAddress(i + "");
            list.add(userInfo);
        }
        int i = userInfoMapper.insertList(list);
        Assert.assertEquals(i, 5);

    }

    /**
     * 插入数据，限制为实体包含`id`属性并且必须为自增列，实体配置的主键策略无效
     * <p>
     * INSERT INTO `t_user_info` ( `name`,`address`,`first_name`,`order_id`,`orderPrice`,`mgr`,`status` ) VALUES ( ?,?,?,?,?,?,? )
     */
    @Test
    public void insertUseGeneratedKeys() {
        UserInfo userInfo = getUserInfo();
        userInfo.setName("keke");
        userInfo.setId(100);
        int i = userInfoMapper.insertUseGeneratedKeys(userInfo);
        Assert.assertEquals(i, 1);
    }

    /**
     * 实体字段添加 @Version 时
     * <p>
     * mysql 乐观锁，决并发问题  version 字段自动更新
     * <p>
     * 读取出数据时，将此版本号一同读出，之后更新时，对此版本号加一
     * 此时，将提交数据的版本数据与数据库表对应记录的当前版本信息进行比对，
     * 如果提交的数据版本号大于数据库表当前版本号，则予以更新，否则认为是过期数据
     * <p>
     * 通用mapper支持的方法
     * delete
     * deleteByPrimaryKey
     * updateByPrimaryKey
     * updateByPrimaryKeySelective
     * updateByExample
     * updateByExampleSelective
     * 这些方法在执行时会更新乐观锁字段的值或者使用乐观锁的值作为查询条件。
     */
    @Test
    public void updateByPrimaryKeyWithVersion() {
        //SELECT `id`,`name`,`address`,`first_name`,`order_id`,`orderPrice`,`mgr`,`status`,`version` FROM `t_user_info` WHERE `id` = ?
        //查询 版本号
        UserInfo userinfo = getUserInfo();
        userinfo.setId(43);
        userinfo = userInfoMapper.selectOne(userinfo);

        //UPDATE `t_user_info` SET `name` = ?,`address` = ?,`first_name` = ?,`order_id` = ?,`orderPrice` = ?,`mgr` = ?,`status` = ?,`version` = ? WHERE `id` = ? AND `version` = ?
        //更新  版本号+1
        userinfo.setId(43);
        userinfo.setAddress("hn");
        int i = 0;
        //int i = userInfoMapper.updateByPrimaryKey(userinfo);
        i = userInfoMapper.updateByPrimaryKeyWithVersion(userinfo);
        Assert.assertThat(i, new IsEqual<>(1));

    }

    /**
     * 实体字段添加 @Version 时
     * <p>
     * mysql 乐观锁 删除
     * <p>
     * 1. 直接删除时，version 不是删除条件
     * 2. 先查询，后删除时，version 为删除条件
     */
    @Test
    public void deleteWithVersion() {
        // 查询SELECT `id`,`name`,`address`,`first_name`,`order_id`,`orderPrice`,`mgr`,`status`,`version` FROM `t_user_info` WHERE `id` = ?
        UserInfo userinfo = getUserInfo();
        userinfo.setId(42);
        userinfo = userInfoMapper.selectOne(userinfo);

        // 删除 DELETE FROM `t_user_info` WHERE `id` = ? AND `name` = ? AND `address` = ? AND `version` = ?
        int i = userInfoMapper.deleteWithVersion(userinfo);
        Assert.assertThat(i, new IsEqual<>(1));
    }


    /**
     * 逻辑删除
     * <p>
     * 实体字段添加 @LogicDelete 注解时，delete 时将逻辑字段更新为0
     *
     * @LogicDelete(isDeletedValue = 0, notDeletedValue = 1)
     * 0：不可用数据
     * 1：可用数据
     * <p>
     */
    @Test
    public void deleteByPrimaryKeyWithLogicDelete() {
        UserInfo userinfo = getUserInfo();
        userinfo.setId(3);
        // SELECT `id`,`name`,`address`,`first_name`,`order_id`,`orderPrice`,`mgr`,`status`,`version` FROM `t_user_info` WHERE `id` = ? AND `status` = 1
        // 查询 可用的数据
        userinfo = userInfoMapper.selectOne(userinfo);
        // 逻辑删除
        // UPDATE `t_user_info` SET `status` = 0 WHERE `id` = ? AND `status` = 1
        userInfoMapper.deleteByPrimaryKey(userinfo);
    }

    /**
     * 逻辑查询和逻辑更新
     * <p>
     * 实体字段添加 @LogicDelete 注解时，
     * <p>
     * select 时只查询 逻辑字段为1的数据，
     * update 时只更新 逻辑字段为1的数据
     */
    @Test
    public void updateByPrimaryKeyWithLogicDelete() {
        UserInfo userinfo = getUserInfo();
        userinfo.setId(3);

        //SELECT `id`,`name`,`address`,`first_name`,`order_id`,`orderPrice`,`mgr`,`status`,`version` FROM `t_user_info` WHERE `id` = ? AND `status` = 1
        // 查询 可用的数据
        userinfo = userInfoMapper.selectOne(userinfo);


        //UPDATE `t_user_info` SET `name` = ?,`address` = ?,`first_name` = ?,`order_id` = ?,`orderPrice` = ?,`mgr` = ?,`status` = 1,`version` = ? WHERE `id` = ? AND `version` = ? AND `status` = 1
        // 更新 可用数据
        userinfo.setAddress("hn");
        int i = userInfoMapper.updateByPrimaryKey(userinfo);

    }

    /**
     * 通用 mapper 扩展
     * <p>
     * 查询 逻辑删除的数据
     */
    //@Test
    //public void selectAllLogicDelete() {
    //    List<UserInfo> list = userInfoMapper.selectAllLogicDelete();
    //    Assert.assertNotNull(list);
    //}
}