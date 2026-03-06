package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.caiwu.Dao.IPxkechengtableDao;
import com.xwcloud.cloud.caiwu.Service.IPxkechengtableService;
import com.xwcloud.cloud.model.entity.Pxkechengtable;
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
public class PxkechengtableServiceImpl extends ServiceImpl<IPxkechengtableDao, Pxkechengtable> implements IPxkechengtableService {
	@Autowired
    IPxkechengtableDao iPxkechengtableDao;
}
