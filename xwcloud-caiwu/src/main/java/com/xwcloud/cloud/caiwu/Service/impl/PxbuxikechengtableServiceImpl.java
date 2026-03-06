package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.caiwu.Dao.IPxbuxikechengtableDao;
import com.xwcloud.cloud.caiwu.Service.IPxbuxikechengtableService;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-15
 */
@Service
public class PxbuxikechengtableServiceImpl extends ServiceImpl<IPxbuxikechengtableDao, Pxbuxikechengtable> implements IPxbuxikechengtableService {
    @Autowired
    IPxbuxikechengtableDao iPxbuxikechengtableDao;
}
