package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.shitingLiushuiVo;
import com.xwcloud.cloud.model.entity.Pxshitingrecordtable;
import com.xwcloud.cloud.zsbm.Dao.IPxshitingrecordtableDao;
import com.xwcloud.cloud.zsbm.Service.IPxshitingrecordtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Service
//@DS("#header.DBname")
public class PxshitingrecordtableServiceImpl extends ServiceImpl<IPxshitingrecordtableDao, Pxshitingrecordtable> implements IPxshitingrecordtableService {

    @Autowired
    IPxshitingrecordtableDao iPxshitingrecordtableDao;

    @Override
    public List<Pxshitingrecordtable> GetShitingRecordsByStuID(long stuID) {
        return iPxshitingrecordtableDao.GetShitingRecordsByStuID(stuID);
    }

    @Override
    public int deleteShitongRecordsByStuID(long stuID) {
        return iPxshitingrecordtableDao.deleteShitongRecordsByStuID(stuID);
    }

    @Override
    public Page<shitingLiushuiVo> GetShitingLiushuiPages(Page<shitingLiushuiVo> page, QueryWrapper<shitingLiushuiVo> wrapper) {
        return iPxshitingrecordtableDao.GetShitingLiushuiPages(page, wrapper);
    }

    @Override
    public List<shitingLiushuiVo> GetShitingLiushuiList(QueryWrapper wrapper) {
        return iPxshitingrecordtableDao.GetShitingLiushuiList(wrapper);
    }
}
