package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.caiwu.Dao.IPxbuxistyletableDao;
import com.xwcloud.cloud.caiwu.Service.IPxbuxistyletableService;
import com.xwcloud.cloud.model.entity.Pxbuxistyletable;
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
public class PxbuxistyletableServiceImpl extends ServiceImpl<IPxbuxistyletableDao, Pxbuxistyletable> implements IPxbuxistyletableService {
    @Autowired
    IPxbuxistyletableDao iPxbuxistyletableDao;
}
