package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxtskaiguandefaulttable;

import com.xwcloud.cloud.pkxk.Dao.IPxtskaiguandefaulttableDao;
import com.xwcloud.cloud.pkxk.Service.IPxtskaiguandefaulttableService;
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
public class PxtskaiguandefaulttableServiceImpl extends ServiceImpl<IPxtskaiguandefaulttableDao, Pxtskaiguandefaulttable> implements IPxtskaiguandefaulttableService {
	@Autowired
    IPxtskaiguandefaulttableDao iPxtskaiguandefaulttableDao;
}
