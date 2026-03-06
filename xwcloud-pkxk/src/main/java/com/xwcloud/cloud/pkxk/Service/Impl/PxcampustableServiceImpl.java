package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxcampustable;
import com.xwcloud.cloud.pkxk.Dao.IPxcampustableDao;
import com.xwcloud.cloud.pkxk.Service.IPxcampustableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-14
 */
@Service
public class PxcampustableServiceImpl extends ServiceImpl<IPxcampustableDao, Pxcampustable> implements IPxcampustableService {
	@Autowired
    IPxcampustableDao iPxcampustableDao;
}
