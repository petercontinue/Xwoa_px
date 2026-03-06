package com.xwcloud.cloud.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

public class MyMetaObjectHandler implements MetaObjectHandler {
    //新增填充
    @Override
    public void insertFill(MetaObject metaObject) {
        Object qiyeID = metaObject.getValue("qiyeID");
        Object addTime = metaObject.getValue("addTime");
        //获取当前登录用户
        //SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
        if (null == qiyeID) {
            metaObject.setValue("qiyeID", "1");
        }
        if (null == addTime) {
            metaObject.setValue("addTime", new Date());
        }
    }

    //更新填充
    @Override
    public void updateFill(MetaObject metaObject) {
        insertFill(metaObject);
    }
}