package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxxiangcetableDao;
import com.xwcloud.cloud.homeschool.Service.IPxxiangcetableService;

import com.xwcloud.cloud.model.Vo.PxxiangcetableVo;
import com.xwcloud.cloud.model.entity.Pxxiangcetable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
@Service
public class PxxiangcetableServiceImpl extends ServiceImpl<IPxxiangcetableDao, Pxxiangcetable> implements IPxxiangcetableService {

    @Override
    public Page<PxxiangcetableVo> getPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getPage(page, wrapper);
    }

    @Override
    public List<PxxiangcetableVo> getJoinList(Wrapper wrapper) {
        return null;
    }
}
