package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxjieke;

import com.xwcloud.cloud.stu.Dao.IPxjiekeDao;
import com.xwcloud.cloud.stu.Service.IPxjiekeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-27
 */
@Service
public class PxjiekeServiceImpl extends ServiceImpl<IPxjiekeDao, Pxjieke> implements IPxjiekeService {
	@Autowired
    IPxjiekeDao iPxjiekeDao;
}
