package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.caiwu.Dao.IPxpaiketableDao;
import com.xwcloud.cloud.caiwu.Service.IPxpaiketableService;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-15
 */
@Service
public class PxpaiketableServiceImpl extends ServiceImpl<IPxpaiketableDao, Pxpaiketable> implements IPxpaiketableService {
    @Override
    public Page<HashMap<String, String>> getStupaikePage(Page page, Wrapper wrapper) {
        return this.baseMapper.getStupaikePage(page,wrapper);
    }

    @Override
    public Page<HashMap<String, String>> getTeacherpaikePage(Page page, Wrapper wrapper) {
        return this.baseMapper.getTeacherpaikePage(page, wrapper);
    }

    @Override
    public Page<HashMap<String, String>> getCampuspaikePage(Page page, Wrapper wrapper) {
        return this.baseMapper.getCampuspaikePage(page, wrapper);
    }

    @Override
    public Page<HashMap<String, String>> getClasspaikePage(Page page, Wrapper wrapper) {
        return this.baseMapper.getClasspaikePage(page, wrapper);
    }
}
