package com.myke.mybaits.plus.manualcode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myke.mybaits.plus.manualcode.entity.User;

/**
 * BaseMapper 继承该接口后，无需编写 mapper.xml 文件，即可获得CRUD功能
 */
public interface UserMapper extends BaseMapper<User> {

}