package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxtousutableDao;
import com.xwcloud.cloud.homeschool.Service.IPxtousutableService;

import com.xwcloud.cloud.model.Vo.PxstuFeedbackVo;
import com.xwcloud.cloud.model.entity.Pxtousutable;
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
public class PxtousutableServiceImpl extends ServiceImpl<IPxtousutableDao, Pxtousutable> implements IPxtousutableService {

    @Override
    public Page<PxstuFeedbackVo> getPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getPage(page,wrapper);
    }

    @Override
    public List<PxstuFeedbackVo> getJoinList(Wrapper wrapper) {
        return this.baseMapper.getJoinList(wrapper);
    }
}
