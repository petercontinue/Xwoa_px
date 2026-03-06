package com.xwcloud.cloud.pkxk.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxpowertable;
import com.xwcloud.cloud.pkxk.Dao.IPxpowertableDao;
import com.xwcloud.cloud.pkxk.Service.IPxpowertableService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-11
 */
@Service
public class PxpowertableServiceImpl extends ServiceImpl<IPxpowertableDao, Pxpowertable> implements IPxpowertableService {
    @Autowired
    IPxpowertableDao iPxpowertableDao;
}
