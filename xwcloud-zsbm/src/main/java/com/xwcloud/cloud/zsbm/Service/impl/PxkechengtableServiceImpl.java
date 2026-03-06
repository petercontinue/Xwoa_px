package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.bixikechengxialaVO;
import com.xwcloud.cloud.model.Vo.kechengListVo;
import com.xwcloud.cloud.model.entity.Pxkechengtable;
import com.xwcloud.cloud.zsbm.Dao.IPxkechengtableDao;
import com.xwcloud.cloud.zsbm.Service.IPxkechengtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-10
 */
@Service
public class PxkechengtableServiceImpl extends ServiceImpl<IPxkechengtableDao, Pxkechengtable> implements IPxkechengtableService {

    @Autowired
    IPxkechengtableDao iPxkechengtableDao;

    @Override
    public Page<kechengListVo> getAllKechengPages(Page page, QueryWrapper wrapper) {
        return iPxkechengtableDao.getAllKechengPages(page, wrapper);
    }

    @Override
    public int UpdateKechengState(Long Id, boolean State) {
        return iPxkechengtableDao.UpdateKechengState(Id, State);
    }

    @Override
    public List<kechengListVo> getAllKechengList(Wrapper wrapper) {
        return iPxkechengtableDao.getAllKechengList(wrapper);
    }

    @Override
    public Pxkechengtable GetKechengById(Long Id) {
        return iPxkechengtableDao.GetKechengById(Id);
    }

    @Override
    public List<bixikechengxialaVO> GetBuxikechengByCampusID(long campusID, long qiyeID, Integer jifeiStyleID) {
        return iPxkechengtableDao.GetBuxikechengByCampusID(campusID, qiyeID, jifeiStyleID);
    }

    @Override
    public List<Pxkechengtable> getYxChabanKc(QueryWrapper<Pxkechengtable> wrapper) {
        return iPxkechengtableDao.getYxChabanKc(wrapper);
    }
}
