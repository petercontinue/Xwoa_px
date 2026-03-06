package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.workdayrecordVo;
import com.xwcloud.cloud.model.entity.Pxworkdayrecordtable;
import com.xwcloud.cloud.sys.Dao.IPxworkdayrecordtableDao;
import com.xwcloud.cloud.sys.Service.IPxworkdayrecordtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-25
 */
@Service
//@DS("#header.DBname")
public class PxworkdayrecordtableServiceImpl extends ServiceImpl<IPxworkdayrecordtableDao, Pxworkdayrecordtable> implements IPxworkdayrecordtableService {

    @Autowired
    IPxworkdayrecordtableDao iPxworkdayrecordtableDao;

    @Override
    public Page<workdayrecordVo> Getworkdayrecords(Page page, QueryWrapper wrapper) {
        return iPxworkdayrecordtableDao.Getworkdayrecords(page,wrapper);
    }

    @Override
    public List<workdayrecordVo> GetWorkdayrecordsList(QueryWrapper wrapper) {
        return iPxworkdayrecordtableDao.GetWorkdayrecordsList(wrapper);
    }
}
