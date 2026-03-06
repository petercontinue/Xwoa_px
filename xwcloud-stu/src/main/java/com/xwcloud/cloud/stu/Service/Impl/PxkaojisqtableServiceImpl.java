package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxkaojisqtable;

import com.xwcloud.cloud.model.Vo.kjSqVo;
import com.xwcloud.cloud.stu.Dao.IPxkaojisqtableDao;
import com.xwcloud.cloud.stu.Service.IPxkaojisqtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Service
public class PxkaojisqtableServiceImpl extends ServiceImpl<IPxkaojisqtableDao, Pxkaojisqtable> implements IPxkaojisqtableService {
    @Autowired
    IPxkaojisqtableDao iPxkaojisqtableDao;

    @Override
    public Page<kjSqVo> getKJsqPage(Page page, QueryWrapper queryWrapper) {
        return iPxkaojisqtableDao.getKJsqPage(page, queryWrapper);
    }
}
