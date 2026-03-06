package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.getclassVo;
import com.xwcloud.cloud.model.Vo.kcInfoVo;
import com.xwcloud.cloud.model.entity.Pxkechengtable;

import com.xwcloud.cloud.pkxk.Dao.IPxkechengtableDao;
import com.xwcloud.cloud.pkxk.Service.IPxkechengtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Service
public class PxkechengtableServiceImpl extends ServiceImpl<IPxkechengtableDao, Pxkechengtable> implements IPxkechengtableService {
    @Autowired
    IPxkechengtableDao iPxkechengtableDao;

    @Override
    public List<kcInfoVo> getnewkcInfoList(Long qiyeID) {
        return iPxkechengtableDao.getnewkcInfoList(qiyeID);
    }

    @Override
    public List<getclassVo> getkcBycampus(Long campusID, Long qiyeID) {
        return iPxkechengtableDao.getkcBycampus(campusID, qiyeID);
    }

    @Override
    public List<getclassVo> getKcToYueXiaoKe(QueryWrapper queryWrapper) {
        return iPxkechengtableDao.getKcToYueXiaoKe(queryWrapper);
    }

    @Override
    public List<getclassVo> GetRenkeTeacherList(long teachSubjectID, long qiyeID) {
        return iPxkechengtableDao.GetRenkeTeacherList(teachSubjectID, qiyeID);
    }
}
