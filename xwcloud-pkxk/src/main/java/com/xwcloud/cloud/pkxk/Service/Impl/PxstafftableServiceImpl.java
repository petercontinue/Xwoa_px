package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.getclassVo;
import com.xwcloud.cloud.model.Vo.haveTimeTeaVo;
import com.xwcloud.cloud.model.entity.Pxstafftable;
import com.xwcloud.cloud.pkxk.Dao.IPxstafftableDao;
import com.xwcloud.cloud.pkxk.Service.IPxstafftableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-11
 */
@Service
public class PxstafftableServiceImpl extends ServiceImpl<IPxstafftableDao, Pxstafftable> implements IPxstafftableService {
    @Autowired
    IPxstafftableDao iPxstafftableDao;

    @Override
    public Page<haveTimeTeaVo> getstaff(Page page, QueryWrapper queryWrapper) {
        return iPxstafftableDao.getstaff(page, queryWrapper);
    }

    @Override
    public List<getclassVo> getstaffBycam(Long campusID, Long qiyeID) {
        return iPxstafftableDao.getstaffBycam(campusID,qiyeID);
    }

    @Override
    public List<getclassVo> getallstaff(Long qiyeID) {
        return iPxstafftableDao.getallstaff(qiyeID);
    }
}
