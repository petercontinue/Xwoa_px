package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxtuisongtable;
import com.xwcloud.cloud.pkxk.Dao.IPxtuisongtableDao;
import com.xwcloud.cloud.pkxk.Service.IPxtuisongtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-04
 */
@Service
public class PxtuisongtableServiceImpl extends ServiceImpl<IPxtuisongtableDao, Pxtuisongtable> implements IPxtuisongtableService {
	@Autowired
    IPxtuisongtableDao iPxtuisongtableDao;
}
