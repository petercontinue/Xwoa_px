package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxcertificatetable;

import com.xwcloud.cloud.model.Vo.ExportZSVo;
import com.xwcloud.cloud.model.Vo.zhengshuSTVo;
import com.xwcloud.cloud.stu.Dao.IPxcertificatetableDao;
import com.xwcloud.cloud.stu.Service.IPxcertificatetableService;
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
public class PxcertificatetableServiceImpl extends ServiceImpl<IPxcertificatetableDao, Pxcertificatetable> implements IPxcertificatetableService {
    @Autowired
    IPxcertificatetableDao iPxcertificatetableDao;

    @Override
    public Page<zhengshuSTVo> getzhengshuPage(Page page, QueryWrapper queryWrapper1, QueryWrapper queryWrapper) {
        return iPxcertificatetableDao.getzhengshuPage(page, queryWrapper1, queryWrapper);
    }

    @Override
    public List<ExportZSVo> Exportzhengshu(Long qiyeID) {
        return iPxcertificatetableDao.Exportzhengshu(qiyeID);
    }

    @Override
    public List<Pxcertificatetable> getcfZS(String zsName, Long qiyeID) {
        return iPxcertificatetableDao.getcfZS(zsName, qiyeID);
    }
}
