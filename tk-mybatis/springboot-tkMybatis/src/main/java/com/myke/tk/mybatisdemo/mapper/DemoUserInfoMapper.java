package com.myke.tk.mybatisdemo.mapper;


import com.myke.tk.mybatisdemo.entiry.DemoUserInfo;
import com.myke.tk.mybatisdemo.entiry.DemoUserInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DemoUserInfoMapper {
    long countByExample(DemoUserInfoExample example);

    int deleteByExample(DemoUserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DemoUserInfo record);

    int insertSelective(DemoUserInfo record);

    List<DemoUserInfo> selectByExample(DemoUserInfoExample example);

    DemoUserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DemoUserInfo record, @Param("example") DemoUserInfoExample example);

    int updateByExample(@Param("record") DemoUserInfo record, @Param("example") DemoUserInfoExample example);

    int updateByPrimaryKeySelective(DemoUserInfo record);

    int updateByPrimaryKey(DemoUserInfo record);
}