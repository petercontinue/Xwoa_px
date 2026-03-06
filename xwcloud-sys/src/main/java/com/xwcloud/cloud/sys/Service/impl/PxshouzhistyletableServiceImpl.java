package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxliushuizhangtable;

import com.xwcloud.cloud.model.entity.Pxshouzhistyletable;
import com.xwcloud.cloud.sys.Dao.IPxshouzhistyletableDao;
import com.xwcloud.cloud.sys.Service.IPxshouzhistyletableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-24
 */
@Service
//@DS("#header.DBname")
public class PxshouzhistyletableServiceImpl extends ServiceImpl<IPxshouzhistyletableDao, Pxshouzhistyletable> implements IPxshouzhistyletableService {

    @Autowired
    IPxshouzhistyletableDao iPxshouzhistyletableDao;

    @Override
    public List<Pxliushuizhangtable> queryLiushuizhang() {
        return iPxshouzhistyletableDao.getallLiushuizhang();
    }
}
