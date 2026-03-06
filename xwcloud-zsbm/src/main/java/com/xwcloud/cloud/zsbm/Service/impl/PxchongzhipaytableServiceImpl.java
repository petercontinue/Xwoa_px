package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.chongzhiPayListVo;
import com.xwcloud.cloud.model.Vo.chongzhixiangqingVO;
import com.xwcloud.cloud.model.entity.Pxchongzhipaytable;

import com.xwcloud.cloud.zsbm.Dao.IPxchongzhipaytableDao;
import com.xwcloud.cloud.zsbm.Service.IPxchongzhipaytableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-12
 */
@Service
public class PxchongzhipaytableServiceImpl extends ServiceImpl<IPxchongzhipaytableDao, Pxchongzhipaytable> implements IPxchongzhipaytableService {
    @Autowired
    IPxchongzhipaytableDao iPxchongzhipaytableDao;

    @Override
    public Page<chongzhiPayListVo> GetUserChongzhiPayListPages(Page page, QueryWrapper wrapper) {
        return iPxchongzhipaytableDao.GetUserChongzhiPayListPages(page, wrapper);
    }

    @Override
    public List<chongzhiPayListVo> GetUserChongzhiPayListList(QueryWrapper wrapper) {
        return iPxchongzhipaytableDao.GetUserChongzhiPayListList(wrapper);
    }

    @Override
    public Page<chongzhixiangqingVO> GetUserChongzhixiangqingPages(Page page, Long qiyeID, long stuID) {
        return iPxchongzhipaytableDao.GetUserChongzhixiangqingPages(page,qiyeID,stuID);
    }
}
