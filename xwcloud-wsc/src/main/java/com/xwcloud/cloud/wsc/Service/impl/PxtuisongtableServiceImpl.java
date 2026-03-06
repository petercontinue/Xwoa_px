package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.wsc.Dao.IPxtuisongtableDao;
import com.xwcloud.cloud.wsc.Service.IPxtuisongtableService;

import com.xwcloud.cloud.model.Vo.messageVO;
import com.xwcloud.cloud.model.entity.Pxtuisongtable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-21
 */
@Service
public class PxtuisongtableServiceImpl extends ServiceImpl<IPxtuisongtableDao, Pxtuisongtable> implements IPxtuisongtableService {

    @Autowired
    IPxtuisongtableDao iPxtuisongtableDao;

    @Override
    public Page<messageVO> GetAllMessagePages(Page page, QueryWrapper wrapper) {
        return iPxtuisongtableDao.GetAllMessagePages(page,wrapper);
    }

    @Override
    public Page<messageVO> GetAllMessageStaffPages(Page page, Wrapper wrapper) {
        return iPxtuisongtableDao.GetAllMessageStaffPages(page,wrapper);
    }
}
