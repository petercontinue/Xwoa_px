package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxqingjiatableDao;
import com.xwcloud.cloud.homeschool.Service.IPxqingjiatableService;

import com.xwcloud.cloud.model.Vo.PxqingjiatableVo;
import com.xwcloud.cloud.model.entity.Pxqingjiatable;
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
public class PxqingjiatableServiceImpl extends ServiceImpl<IPxqingjiatableDao, Pxqingjiatable> implements IPxqingjiatableService {

    @Override
    public Page<PxqingjiatableVo> getPage(Page<PxqingjiatableVo> page, Wrapper wrapper) {
        return this.baseMapper.getPxqingjiatableJoinPage(page,wrapper);
    }

    @Override
    public List<PxqingjiatableVo> getJoinList(Wrapper wrapper) {
        return this.baseMapper.getPxqingjiatableJoinList(wrapper);
    }
}
