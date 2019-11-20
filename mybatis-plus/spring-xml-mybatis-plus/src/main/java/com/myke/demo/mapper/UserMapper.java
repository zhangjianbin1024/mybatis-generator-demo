package com.myke.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myke.demo.entity.User;

public interface UserMapper extends BaseMapper<User> {

    public Integer selectMaxAge();

}
