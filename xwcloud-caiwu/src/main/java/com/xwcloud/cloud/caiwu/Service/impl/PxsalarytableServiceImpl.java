package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.IPxsalarytableDao;
import com.xwcloud.cloud.caiwu.Service.IPxsalarytableService;
import com.xwcloud.cloud.model.Vo.PxsalarytableVo;
import com.xwcloud.cloud.model.entity.Pxsalarystyletable;
import com.xwcloud.cloud.model.entity.Pxsalarytable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-17
 */
@Service
public class PxsalarytableServiceImpl extends ServiceImpl<IPxsalarytableDao, Pxsalarytable> implements IPxsalarytableService {
    @Autowired
    IPxsalarytableDao iPxsalarytableDao;

    @Override
    public Page<PxsalarytableVo> getPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getPage(page, wrapper);
    }

    @Override
    public PxsalarytableVo getGongzi(Wrapper wrapper) {
        return this.baseMapper.getGongzi(wrapper);
    }

    @Override
    public List<PxsalarytableVo> getJoinList(Wrapper wrapper) {
        return this.baseMapper.getJoinList(wrapper);
    }

    @Override
    public List<Pxsalarystyletable> getGongziPro(Wrapper wrapper) {
        return this.baseMapper.getGongziPro(wrapper);
    }

    @Override
    public List<HashMap<String, String>> getGongzitiao(Wrapper wrapper, List<String> stylelist) {
        return null;
    }


    @Override
    public List<PxsalarytableVo> GetSalaryList(QueryWrapper wrapper) {
        return this.baseMapper.GetSalaryList(wrapper);
    }
}
