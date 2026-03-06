package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.log.Logxjbtable;
import com.xwcloud.cloud.model.Vo.LogxjbVo;
import com.xwcloud.cloud.sys.Dao.ILogxjbtableDao;
import com.xwcloud.cloud.sys.Service.ILogxjbtableService;
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
public class LogxjbtableServiceImpl extends ServiceImpl<ILogxjbtableDao, Logxjbtable> implements ILogxjbtableService {
    @Autowired
    ILogxjbtableDao iLogxjbtableDao;


    @Override
    public Page<LogxjbVo> getLogxjbInfo(Page page, QueryWrapper wrapper) {
        return iLogxjbtableDao.getLogxjbInfo(page, wrapper);
    }

    @Override
    public List<LogxjbVo> getLogxjbInfolist(QueryWrapper queryWrapper) {
        return iLogxjbtableDao.getLogxjbInfolist(queryWrapper);
    }
}
