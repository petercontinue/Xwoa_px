package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxzuoyetableDao;
import com.xwcloud.cloud.homeschool.Service.IPxzuoyetableService;
import com.xwcloud.cloud.model.entity.Pxzuoyetable;
import com.xwcloud.cloud.model.Vo.PxzuoyetableVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
public class PxzuoyetableServiceImpl extends ServiceImpl<IPxzuoyetableDao, Pxzuoyetable> implements IPxzuoyetableService {

    @Override
    public Page<PxzuoyetableVo> getPage(Page<PxzuoyetableVo> page, Wrapper wrapper) {
        return this.baseMapper.getPage(page,wrapper);
    }

    @Override
    public List<PxzuoyetableVo> getJoinList(Wrapper wrapper) {
        return this.baseMapper.getJoinList(wrapper);
    }

    @Override
    public Page<HashMap<String, String>> getZuoyeDetaile(Page page, Wrapper wrapper) {
        return this.baseMapper.getZuoyeDetaile(page,wrapper);
    }
}
