package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliesorderstable;

import com.xwcloud.cloud.model.Vo.jinxiaocunXSliushuiVo;
import com.xwcloud.cloud.zsbm.Dao.IPxteachingsuppliesorderstableDao;
import com.xwcloud.cloud.zsbm.Service.IPxteachingsuppliesorderstableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-21
 */
@Service
public class PxteachingsuppliesorderstableServiceImpl extends ServiceImpl<IPxteachingsuppliesorderstableDao, Pxteachingsuppliesorderstable> implements IPxteachingsuppliesorderstableService {

    @Autowired
    IPxteachingsuppliesorderstableDao iPxteachingsuppliesorderstableDao;

    @Override
    public Page<jinxiaocunXSliushuiVo> getXiaoshouLiushuiDays(Page page, QueryWrapper wrapper) {
        return iPxteachingsuppliesorderstableDao.getXiaoshouLiushuiDays(page, wrapper);
    }

    @Override
    public Page<jinxiaocunXSliushuiVo> GetTodayXiaoshouliushui(Page page, long qiyeID) {
        return iPxteachingsuppliesorderstableDao.GetTodayXiaoshouliushui(page, qiyeID);
    }

    @Override
    public List<jinxiaocunXSliushuiVo> GetAllXiaoshouliushuiList(QueryWrapper wrapper) {
        return iPxteachingsuppliesorderstableDao.GetAllXiaoshouliushuiList(wrapper);
    }
}
