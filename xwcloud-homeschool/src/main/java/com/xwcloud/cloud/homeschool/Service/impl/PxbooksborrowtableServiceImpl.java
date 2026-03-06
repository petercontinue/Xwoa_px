package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxbooksborrowtableDao;
import com.xwcloud.cloud.homeschool.Service.IPxbooksborrowtableService;

import com.xwcloud.cloud.model.Vo.PxbooksborrowtableVo;
import com.xwcloud.cloud.model.entity.Pxbooksborrowtable;
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
public class PxbooksborrowtableServiceImpl extends ServiceImpl<IPxbooksborrowtableDao, Pxbooksborrowtable> implements IPxbooksborrowtableService {

    @Override
    public Page<PxbooksborrowtableVo> getPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getPage(page, wrapper);
    }

    @Override
    public List<PxbooksborrowtableVo> getJoinList(Wrapper wrapper) {
        return this.baseMapper.getJoinList(wrapper);
    }
}
