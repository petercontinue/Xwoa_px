package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.IPxyxqiandantableDao;
import com.xwcloud.cloud.caiwu.Service.IPxyxqiandantableService;
import com.xwcloud.cloud.model.entity.Pxyxqiandantable;

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
public class PxyxqiandantableServiceImpl extends ServiceImpl<IPxyxqiandantableDao, Pxyxqiandantable> implements IPxyxqiandantableService {

    @Override
    public Page<HashMap<String, String>> getYixiangDetail(Page page, Wrapper wrapper) {
        return this.baseMapper.getYxtongjiDetail(page,wrapper);
    }

    @Override
    public Page<HashMap<String, String>> getYixiangPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getYixiangPage(page, wrapper);
    }

    @Override
    public List<HashMap<String, Object>> getYixiangList(Wrapper wrapper) {
        return this.baseMapper.getYixiangList(wrapper);
    }
}
