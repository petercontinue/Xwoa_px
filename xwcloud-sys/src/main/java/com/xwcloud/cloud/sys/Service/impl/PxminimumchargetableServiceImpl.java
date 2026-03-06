package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.minimumchargeVo;
import com.xwcloud.cloud.model.entity.Pxminimumchargetable;
import com.xwcloud.cloud.sys.Dao.IPxminimumchargetableDao;
import com.xwcloud.cloud.sys.Service.IPxminimumchargetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-22
 */
@Service
//@DS("#header.DBname")
public class PxminimumchargetableServiceImpl extends ServiceImpl<IPxminimumchargetableDao, Pxminimumchargetable> implements IPxminimumchargetableService {

    @Autowired
    IPxminimumchargetableDao iPxminimumchargetableDao;

    @Override
    public Page<minimumchargeVo> GetShoufeiBiaozhunPages(Page page, Wrapper wrapper) {
        return iPxminimumchargetableDao.GetShoufeiBiaozhunPages(page, wrapper);
    }
}
