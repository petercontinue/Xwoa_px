package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxliuyantable;
import com.xwcloud.cloud.zsbm.Dao.IPxliuyantableDao;
import com.xwcloud.cloud.zsbm.Service.IPxliuyantableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-25
 */
@Service
//@DS("#header.DBname")
public class PxliuyantableServiceImpl extends ServiceImpl<IPxliuyantableDao, Pxliuyantable> implements IPxliuyantableService {

    @Autowired
    IPxliuyantableDao iPxliuyantableDao;

    @Override
    public Page<Pxliuyantable> GetAllLiuyanPages(Page page, QueryWrapper wrapper) {
        return iPxliuyantableDao.GetAllLiuyanPages(page, wrapper);
    }
}
