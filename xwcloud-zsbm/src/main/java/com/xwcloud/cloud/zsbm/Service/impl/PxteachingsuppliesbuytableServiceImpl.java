package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.caigoushenqingVo;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliesbuytable;

import com.xwcloud.cloud.zsbm.Dao.IPxteachingsuppliesbuytableDao;
import com.xwcloud.cloud.zsbm.Service.IPxteachingsuppliesbuytableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
@Service
public class PxteachingsuppliesbuytableServiceImpl extends ServiceImpl<IPxteachingsuppliesbuytableDao, Pxteachingsuppliesbuytable> implements IPxteachingsuppliesbuytableService {

    @Autowired
    IPxteachingsuppliesbuytableDao iPxteachingsuppliesbuytableDao;
    @Override
    public Page<caigoushenqingVo> GetTeachingSuppliesbuyPages(Page page, QueryWrapper wrapper) {
        return iPxteachingsuppliesbuytableDao.GetTeachingSuppliesbuyPages(page,wrapper);
    }
}
