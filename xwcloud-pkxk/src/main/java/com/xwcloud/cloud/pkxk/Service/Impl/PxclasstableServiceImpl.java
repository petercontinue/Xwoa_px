package com.xwcloud.cloud.pkxk.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.getclassVo;
import com.xwcloud.cloud.model.entity.Pxclasstable;

import com.xwcloud.cloud.pkxk.Dao.IPxclasstableDao;
import com.xwcloud.cloud.pkxk.Service.IPxclasstableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-01
 */
@Service
public class PxclasstableServiceImpl extends ServiceImpl<IPxclasstableDao, Pxclasstable> implements IPxclasstableService {
    @Autowired
    IPxclasstableDao iPxclasstableDao;

    @Override
    public List<getclassVo> getclassbycam(Long campusID, Long qiyeID) {
        return iPxclasstableDao.getclassbycam(campusID, qiyeID);
    }

    @Override
    public List<getclassVo> NOpaikegetclass(QueryWrapper queryWrapper) {
        return iPxclasstableDao.NOpaikegetclass(queryWrapper);
    }
}
