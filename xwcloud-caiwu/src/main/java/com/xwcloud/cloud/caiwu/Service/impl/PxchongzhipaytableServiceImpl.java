package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.caiwu.Dao.IPxchongzhipaytableDao;
import com.xwcloud.cloud.caiwu.Service.IPxchongzhipaytableService;
import com.xwcloud.cloud.model.entity.Pxchongzhipaytable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-16
 */
@Service
public class PxchongzhipaytableServiceImpl extends ServiceImpl<IPxchongzhipaytableDao, Pxchongzhipaytable> implements IPxchongzhipaytableService {
    @Autowired
    IPxchongzhipaytableDao iPxchongzhipaytableDao;
}
