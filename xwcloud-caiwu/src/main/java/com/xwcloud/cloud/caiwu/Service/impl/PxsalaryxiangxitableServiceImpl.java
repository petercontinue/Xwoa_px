package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.caiwu.Dao.IPxsalaryxiangxitableDao;
import com.xwcloud.cloud.caiwu.Service.IPxsalaryxiangxitableService;
import com.xwcloud.cloud.model.Vo.PxsalaryxiangxitableVo;
import com.xwcloud.cloud.model.entity.Pxsalaryxiangxitable;
import org.springframework.stereotype.Service;

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
public class PxsalaryxiangxitableServiceImpl extends ServiceImpl<IPxsalaryxiangxitableDao, Pxsalaryxiangxitable> implements IPxsalaryxiangxitableService {
    @Override
    public List<PxsalaryxiangxitableVo> getxiangxiList(Wrapper wrapper) {
        return this.baseMapper.getxiangxiList(wrapper);
    }
}
