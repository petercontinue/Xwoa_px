package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.caiwu.Dao.IPxclasstableDao;
import com.xwcloud.cloud.caiwu.Service.IPxclasstableService;
import com.xwcloud.cloud.model.entity.Pxclasstable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-15
 */
@Service
public class PxclasstableServiceImpl extends ServiceImpl<IPxclasstableDao, Pxclasstable> implements IPxclasstableService {
    @Autowired
    IPxclasstableDao iPxclasstableDao;
}
