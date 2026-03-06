package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxbooksaddtableDao;
import com.xwcloud.cloud.homeschool.Service.IPxbooksaddtableService;

import com.xwcloud.cloud.model.Vo.PxbooksaddtableVo;
import com.xwcloud.cloud.model.entity.Pxbooksaddtable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
@DS("user")
public class PxbooksaddtableServiceImpl extends ServiceImpl<IPxbooksaddtableDao, Pxbooksaddtable> implements IPxbooksaddtableService {

    @Override
    public Page<PxbooksaddtableVo> getPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getPage(page, wrapper);
    }

    @Override
    public List<PxbooksaddtableVo> getJoinList(Wrapper wrapper) {
        return this.baseMapper.getJoinList(wrapper);
    }

    @Override
    public List<HashMap<String, String>> gettestlist() {
        return this.baseMapper.getList();
    }
}
