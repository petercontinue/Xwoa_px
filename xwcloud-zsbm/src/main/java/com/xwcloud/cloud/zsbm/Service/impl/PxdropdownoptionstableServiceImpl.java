package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxdropdownoptionstable;
import com.xwcloud.cloud.zsbm.Dao.IPxdropdownoptionstableDao;
import com.xwcloud.cloud.zsbm.Service.IPxdropdownoptionstableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-27
 */
@Service
public class PxdropdownoptionstableServiceImpl extends ServiceImpl<IPxdropdownoptionstableDao, Pxdropdownoptionstable> implements IPxdropdownoptionstableService {
    @Autowired
    IPxdropdownoptionstableDao iPxdropdownoptionstableDao;

}
