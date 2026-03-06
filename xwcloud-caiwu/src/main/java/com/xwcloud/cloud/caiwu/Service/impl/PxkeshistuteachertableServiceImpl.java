package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.IPxkeshistuteachertableDao;
import com.xwcloud.cloud.caiwu.Service.IPxkeshistuteachertableService;
import com.xwcloud.cloud.model.Vo.xflvVo;
import com.xwcloud.cloud.model.entity.Pxkeshistuteachertable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-02
 */
@Service
public class PxkeshistuteachertableServiceImpl extends ServiceImpl<IPxkeshistuteachertableDao, Pxkeshistuteachertable> implements IPxkeshistuteachertableService {

    @Override
    public Page<HashMap<String, String>> getRenewPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getRenewPage(page, wrapper);
    }

    @Override
    public Page<xflvVo> getallshuju(Page page, QueryWrapper queryWrapper,QueryWrapper queryWrapper1) {
        return this.baseMapper.getallshuju(page, queryWrapper,queryWrapper1);
    }

}
