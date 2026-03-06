package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.wsc.Dao.IPxzuoyetableDao;
import com.xwcloud.cloud.wsc.Service.IPxzuoyetableService;
import com.xwcloud.cloud.model.Vo.zuoyeVO;
import com.xwcloud.cloud.model.entity.Pxzuoyetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-07
 */
@Service
public class PxzuoyetableServiceImpl extends ServiceImpl<IPxzuoyetableDao, Pxzuoyetable> implements IPxzuoyetableService {

    @Autowired
    IPxzuoyetableDao iPxzuoyetableDao;

    @Override
    public Page<zuoyeVO> GetAllstuZuoyeList(Page page, QueryWrapper wrapper) {
        return iPxzuoyetableDao.GetAllstuZuoyeList(page, wrapper);
    }

    @Override
    public List<zuoyeVO> GetZuoyexiangqing(QueryWrapper wrapper) {
        return iPxzuoyetableDao.GetZuoyexiangqing(wrapper);
    }
}
