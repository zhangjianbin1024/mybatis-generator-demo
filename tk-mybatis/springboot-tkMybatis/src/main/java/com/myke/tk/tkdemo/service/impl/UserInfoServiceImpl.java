package com.myke.tk.tkdemo.service.impl;

import com.myke.service.impl.BaseMySqlCrudServiceImpl;
import com.myke.tk.tkdemo.entity.UserInfo;
import com.myke.tk.tkdemo.service.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends BaseMySqlCrudServiceImpl<UserInfo> implements UserInfoService {

    /**
     * Creating a new SqlSession
     * Registering transaction synchronization for SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@b5b9333]
     * …………………………sql
     * Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@b5b9333]
     * Transaction synchronization committing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@b5b9333]
     * Transaction synchronization deregistering SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@b5b9333]
     * Transaction synchronization closing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@b5b9333]
     *
     * @param record
     * @return
     */
    //@Transactional
    //@Override
    //public int insert(UserInfo record) {
    //    return super.insert(record);
    //}
}
