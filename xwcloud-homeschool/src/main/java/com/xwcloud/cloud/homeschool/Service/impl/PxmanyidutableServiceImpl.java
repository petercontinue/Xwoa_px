package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxmanyidutableDao;
import com.xwcloud.cloud.homeschool.Service.IPxmanyidutableService;

import com.xwcloud.cloud.model.Vo.PxmanyidutableVo;
import com.xwcloud.cloud.model.entity.Pxmanyidutable;
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
public class PxmanyidutableServiceImpl extends ServiceImpl<IPxmanyidutableDao, Pxmanyidutable> implements IPxmanyidutableService {

    @Override
    public Page<PxmanyidutableVo> getPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getPage(page, wrapper);
    }

    @Override
    public List<PxmanyidutableVo> getJoinList(Wrapper wrapper) {
        return null;
    }
}
