package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.SutClearVo;
import com.xwcloud.cloud.model.entity.Pxkeshiresettable;
import com.xwcloud.cloud.pkxk.Dao.IPxkeshiresettableDao;
import com.xwcloud.cloud.pkxk.Service.IPxkeshiresettableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-08
 */
@Service
public class PxkeshiresettableServiceImpl extends ServiceImpl<IPxkeshiresettableDao, Pxkeshiresettable> implements IPxkeshiresettableService {
    @Autowired
    IPxkeshiresettableDao iPxkeshiresettableDao;

    @Override
    public Page<SutClearVo> getClearPage(Page page, QueryWrapper queryWrapper) {
        return iPxkeshiresettableDao.getClearPage(page, queryWrapper);
    }

    @Override
    public List<SutClearVo> ExporestuClear(QueryWrapper queryWrapper) {
        return iPxkeshiresettableDao.ExporestuClear(queryWrapper);
    }

    @Override
    public String getClearkeshiMoney(Long stuID, Long qiyeID) {
        return iPxkeshiresettableDao.getClearkeshiMoney(stuID, qiyeID);
    }
}
