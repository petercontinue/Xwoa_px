package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.IPxsalarystaffposttableDao;
import com.xwcloud.cloud.caiwu.Service.IPxsalarystaffposttableService;
import com.xwcloud.cloud.model.Vo.PxsalarystaffposttableVo;
import com.xwcloud.cloud.model.entity.Pxsalarystaffposttable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-25
 */
@Service
public class PxsalarystaffposttableServiceImpl extends ServiceImpl<IPxsalarystaffposttableDao, Pxsalarystaffposttable> implements IPxsalarystaffposttableService {

    @Override
    public Page<PxsalarystaffposttableVo> getPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getPage(page, wrapper);
    }

    @Override
    public List<PxsalarystaffposttableVo> getJoinList(Wrapper wrapper) {
        return this.baseMapper.getJoinList(wrapper);
    }

    @Override
    public List<HashMap<String,String>> getGongzixiangmuList(Wrapper wrapper) {
        return this.baseMapper.getGongzixiangmuList(wrapper);
    }
}
