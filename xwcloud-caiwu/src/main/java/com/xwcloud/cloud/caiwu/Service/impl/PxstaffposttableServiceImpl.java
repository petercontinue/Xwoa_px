package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.caiwu.Dao.IPxstaffposttableDao;
import com.xwcloud.cloud.caiwu.Service.IPxstaffposttableService;
import com.xwcloud.cloud.model.entity.Pxstaffposttable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-10
 */
@Service
public class PxstaffposttableServiceImpl extends ServiceImpl<IPxstaffposttableDao, Pxstaffposttable> implements IPxstaffposttableService {
    @Autowired
    IPxstaffposttableDao iPxstaffposttableDao;
}
