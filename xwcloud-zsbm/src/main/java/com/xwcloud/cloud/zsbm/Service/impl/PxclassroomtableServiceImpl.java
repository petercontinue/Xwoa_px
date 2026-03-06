package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxclassroomtable;
import com.xwcloud.cloud.zsbm.Dao.IPxclassroomtableDao;
import com.xwcloud.cloud.zsbm.Service.IPxclassroomtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-07
 */
@Service
//@DS("#header.DBname")
public class PxclassroomtableServiceImpl extends ServiceImpl<IPxclassroomtableDao, Pxclassroomtable> implements IPxclassroomtableService {

    @Autowired
    private IPxclassroomtableDao pxclassroomtableDao;

    @Override
    public List<Pxclassroomtable> getClassRoom(QueryWrapper<Pxclassroomtable> wrapper) {
        return pxclassroomtableDao.getClassRoom(wrapper);
    }
}
