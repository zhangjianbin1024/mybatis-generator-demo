package com.myke.mybaits.plus.plusdemo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myke.mybaits.plus.plusdemo.entity.TUserInfo;
import com.myke.mybaits.plus.plusdemo.service.ITUserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TUserInfoServiceImplTest extends TUserInfoServiceImpl {

    @Autowired
    private ITUserInfoService tUserInfoService;

    @Test
    public void select() {
        List<TUserInfo> list = tUserInfoService.list();
        System.out.println(list);
    }


    @Test
    public void updateById() {
        TUserInfo tUserInfo = new TUserInfo();
        tUserInfo.setId(5);
        TUserInfo entity = tUserInfoService.getById(tUserInfo);

        entity.setId(5);
        entity.setName("zhangbj");
        entity.setOrderId(20202l);
        boolean b = tUserInfoService.updateById(entity);
        System.out.println("update:" + b);
    }

    @Test
    public void save() {
        TUserInfo entity = new TUserInfo();
        entity.setName("mykekek");
        boolean save = tUserInfoService.save(entity);
        System.out.println("save:" + save);
    }

    @Test
    public void removeById() {
        TUserInfo tUserInfo = new TUserInfo();
        tUserInfo.setId(10);
        boolean b = tUserInfoService.removeById(tUserInfo);
        System.out.println("removeById:" + b);
    }

    @Test
    public void page() {
        IPage<TUserInfo> page = new Page<>();
        page.setCurrent(3);
        IPage<TUserInfo> result = tUserInfoService.page(page);
        List<TUserInfo> records = result.getRecords();
        System.out.println(records);

    }

    @Test
    public void insert() {
        TUserInfo tUserInfo = new TUserInfo();
        tUserInfo.setAddress("zhang");
        tUserInfo.setName("keke");
        boolean save = tUserInfoService.save(tUserInfo);
        System.out.println("save:" + save);
    }


}