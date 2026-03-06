package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.IPxoldschooltableDao;
import com.xwcloud.cloud.caiwu.Service.IPxoldschooltableService;
import com.xwcloud.cloud.model.entity.Pxoldschooltable;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-01
 */
@Service
public class PxoldschooltableServiceImpl extends ServiceImpl<IPxoldschooltableDao, Pxoldschooltable> implements IPxoldschooltableService {

    @Override
    public Page<HashMap<String, String>> getOldschoolDetail(Page page, Wrapper wrapper) {
        return this.baseMapper.getOldschoolDetail(page,wrapper);
    }

    @Override
    public List<HashMap<String, String>> getOldschoolBili(Wrapper wrapper) {
        return this.baseMapper.getOldschoolBili(wrapper);
    }
}
