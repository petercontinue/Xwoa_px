package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxdaijinquantable;
import com.xwcloud.cloud.model.zsbm.Vo.daijinquanVo;
import com.xwcloud.cloud.zsbm.Dao.IPxdaijinquantableDao;
import com.xwcloud.cloud.zsbm.Service.IPxdaijinquantableService;
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
public class PxdaijinquantableServiceImpl extends ServiceImpl<IPxdaijinquantableDao, Pxdaijinquantable> implements IPxdaijinquantableService {

    @Autowired
    IPxdaijinquantableDao iPxdaijinquantableDao;

    @Override
    public Pxdaijinquantable GetDaijinquanByID(Long qiandanID) {
        return iPxdaijinquantableDao.GetDaijinquanByID(qiandanID);
    }

    @Override
    public List<Pxdaijinquantable> GetDaijinquanListByStuID(Long stuID) {
        return iPxdaijinquantableDao.GetDaijinquanListByStuID(stuID);
    }

    @Override
    public Integer DeleteDaijinquanByStuID(Long stuID) {
        return iPxdaijinquantableDao.DeleteDaijinquanByStuID(stuID);
    }

    @Override
    public Page<daijinquanVo> GetDaijinquanLiushuiPages(Page page, QueryWrapper wrapper) {
        return iPxdaijinquantableDao.GetDaijinquanLiushuiPages(page, wrapper);
    }

    @Override
    public List<daijinquanVo> GetDaijinquanLiushuiList(QueryWrapper wrapper) {
        return iPxdaijinquantableDao.GetDaijinquanLiushuiList(wrapper);
    }
}
