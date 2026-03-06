package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxbooksreturntableDao;
import com.xwcloud.cloud.homeschool.Service.IPxbooksreturntableService;

import com.xwcloud.cloud.model.Vo.PxbooksreturntableVo;
import com.xwcloud.cloud.model.entity.Pxbooksreturntable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-13
 */
@Service
public class PxbooksreturntableServiceImpl extends ServiceImpl<IPxbooksreturntableDao, Pxbooksreturntable> implements IPxbooksreturntableService {

    @Override
    public Page<PxbooksreturntableVo> getPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getPage(page, wrapper);
    }

    @Override
    public List<PxbooksreturntableVo> getJoinList(Wrapper wrapper) {
        return this.baseMapper.getJoinList(wrapper);
    }
}
