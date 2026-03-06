package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.caiwu.Dao.IPxcampustableDao;
import com.xwcloud.cloud.caiwu.Service.IPxcampustableService;
import com.xwcloud.cloud.model.entity.Pxcampustable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-10
 */
@Service
public class PxcampustableServiceImpl extends ServiceImpl<IPxcampustableDao, Pxcampustable> implements IPxcampustableService {
	@Autowired
    IPxcampustableDao iPxcampustableDao;
}
