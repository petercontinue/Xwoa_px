package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.IPxyxtelfromtableDao;
import com.xwcloud.cloud.caiwu.Service.IPxyxtelfromtableService;
import com.xwcloud.cloud.model.entity.Pxyxtelfromtable;

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
public class PxyxtelfromtableServiceImpl extends ServiceImpl<IPxyxtelfromtableDao, Pxyxtelfromtable> implements IPxyxtelfromtableService {

    @Override
    public Page<HashMap<String, String>> getZhaoshengList(Page<HashMap<String,String>> page,Wrapper wrapper) {
        return this.baseMapper.getZhaoshengList(page,wrapper);
    }

    @Override
    public List<HashMap<String, String>> getZhaoshengBili(Wrapper wrapper) {
        return this.baseMapper.getZhaoshengBili(wrapper);
    }

    @Override
    public List<HashMap<String, String>> getLaiyuantujingList(Wrapper wrapper) {
        return this.baseMapper.getLaiyuantujingList(wrapper);
    }

}
