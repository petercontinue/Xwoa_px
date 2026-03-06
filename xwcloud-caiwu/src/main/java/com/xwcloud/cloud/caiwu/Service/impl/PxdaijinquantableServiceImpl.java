package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.caiwu.Dao.IPxdaijinquantableDao;
import com.xwcloud.cloud.caiwu.Service.IPxdaijinquantableService;
import com.xwcloud.cloud.model.entity.Pxdaijinquantable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-12
 */
@Service
public class PxdaijinquantableServiceImpl extends ServiceImpl<IPxdaijinquantableDao, Pxdaijinquantable> implements IPxdaijinquantableService {
	@Autowired
    IPxdaijinquantableDao iPxdaijinquantableDao;
}
