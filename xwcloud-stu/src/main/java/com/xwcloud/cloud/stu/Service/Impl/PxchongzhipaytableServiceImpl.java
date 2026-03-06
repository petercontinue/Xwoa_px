package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.entity.Pxchongzhipaytable;
import com.xwcloud.cloud.stu.Dao.IPxchongzhipaytableDao;
import com.xwcloud.cloud.stu.Service.IPxchongzhipaytableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-20
 */
@Service
public class PxchongzhipaytableServiceImpl extends ServiceImpl<IPxchongzhipaytableDao, Pxchongzhipaytable> implements IPxchongzhipaytableService {
	@Autowired
    IPxchongzhipaytableDao iPxchongzhipaytableDao;
}
