package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.caiwu.Dao.IPxpaiketeachertableDao;
import com.xwcloud.cloud.caiwu.Service.IPxpaiketeachertableService;
import com.xwcloud.cloud.model.entity.Pxpaiketeachertable;
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
public class PxpaiketeachertableServiceImpl extends ServiceImpl<IPxpaiketeachertableDao, Pxpaiketeachertable> implements IPxpaiketeachertableService {
    @Autowired
    IPxpaiketeachertableDao iPxpaiketeachertableDao;
}
