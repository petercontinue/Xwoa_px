package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxgradeupdatetable;

import com.xwcloud.cloud.stu.Dao.IPxgradeupdatetableDao;
import com.xwcloud.cloud.stu.Service.IPxgradeupdatetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-18
 */
@Service
public class PxgradeupdatetableServiceImpl extends ServiceImpl<IPxgradeupdatetableDao, Pxgradeupdatetable> implements IPxgradeupdatetableService {
	@Autowired
    IPxgradeupdatetableDao iPxgradeupdatetableDao;
}
