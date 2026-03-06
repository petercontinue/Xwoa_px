package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxsubjecttable;
import com.xwcloud.cloud.wsc.Dao.IPxsubjecttableDao;
import com.xwcloud.cloud.wsc.Service.IPxsubjecttableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-31
 */
@Service
public class PxsubjecttableServiceImpl extends ServiceImpl<IPxsubjecttableDao, Pxsubjecttable> implements IPxsubjecttableService {
    @Autowired
    IPxsubjecttableDao iPxsubjecttableDao;
}
