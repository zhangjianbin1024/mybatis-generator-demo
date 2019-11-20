package com.myke.framework.mybatisplus.handlers;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 通用表字段填充类 适用于mybatis plus
 */
public class CommonMetaObjectHandler implements MetaObjectHandler {
    private Logger logger = LoggerFactory.getLogger(CommonMetaObjectHandler.class);

    /**
     * 创建时间
     */
    private final String createTime = "createTime";
    /**
     * 修改时间
     */
    private final String updateTime = "updateTime";

    /**
     * 创建人
     */
    private final String creator = "creator";

    /**
     * 修改者
     */
    private final String modify = "modifier";


    @Override
    public void insertFill(MetaObject metaObject) {
        logger.warn("---------->> insertFill ");
        setInsertFieldValByName(createTime, new Date(), metaObject);
        setInsertFieldValByName(creator, currentUser(), metaObject);
        setInsertFieldValByName(updateTime, new Date(), metaObject);
        setInsertFieldValByName(modify, currentUser(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        logger.warn("---------->> updateFill ");
        setUpdateFieldValByName(updateTime, new Date(), metaObject);
        setUpdateFieldValByName(modify, currentUser(), metaObject);
    }

    /**
     * 获取当前用户
     */
    private String currentUser() {
        return "system";
    }

}
