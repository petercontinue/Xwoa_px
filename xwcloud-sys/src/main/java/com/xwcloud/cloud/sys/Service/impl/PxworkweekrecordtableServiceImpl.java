package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxworkweekrecordtable;

import com.xwcloud.cloud.model.Vo.PxworkweekrecordVo;
import com.xwcloud.cloud.sys.Dao.IPxworkweekrecordtableDao;
import com.xwcloud.cloud.sys.Service.IPxworkweekrecordtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-25
 */
@Service
public class PxworkweekrecordtableServiceImpl extends ServiceImpl<IPxworkweekrecordtableDao, Pxworkweekrecordtable> implements IPxworkweekrecordtableService {

    @Autowired
    IPxworkweekrecordtableDao iPxworkweekrecordtableDao;
    @Override
    public Page<PxworkweekrecordVo> Getworkweekrecords(Page page, QueryWrapper wrapper) {
        return iPxworkweekrecordtableDao.Getworkweekrecords(page,wrapper);
    }

    @Override
    public List<PxworkweekrecordVo> getworkweekrecordsList(QueryWrapper wrapper) {
        return iPxworkweekrecordtableDao.getworkweekrecordsList(wrapper);
    }
}
