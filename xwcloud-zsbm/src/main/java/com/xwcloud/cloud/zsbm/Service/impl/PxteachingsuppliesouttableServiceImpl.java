package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.outjiluVo;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliesouttable;
import com.xwcloud.cloud.zsbm.Dao.IPxteachingsuppliesouttableDao;
import com.xwcloud.cloud.zsbm.Service.IPxteachingsuppliesouttableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-14
 */
@Service
public class PxteachingsuppliesouttableServiceImpl extends ServiceImpl<IPxteachingsuppliesouttableDao, Pxteachingsuppliesouttable> implements IPxteachingsuppliesouttableService {

    @Autowired
    IPxteachingsuppliesouttableDao iPxteachingsuppliesouttableDao;

    @Override
    public Page<outjiluVo> GetTeachingSuppliesOutPages(Page page, QueryWrapper wrapper) {
        return iPxteachingsuppliesouttableDao.GetTeachingSuppliesOutPages(page, wrapper);
    }

    @Override
    public List<outjiluVo> GetTeachingSuppliesOutList(QueryWrapper wrapper) {
        return iPxteachingsuppliesouttableDao.GetTeachingSuppliesOutList(wrapper);
    }
}
