package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxsubjecttable;
import com.xwcloud.cloud.sys.Dao.IPxsubjecttableDao;
import com.xwcloud.cloud.sys.Service.IPxsubjecttableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-10
 */
@Service
public class PxsubjecttableServiceImpl extends ServiceImpl<IPxsubjecttableDao, Pxsubjecttable> implements IPxsubjecttableService {

    @Autowired
    IPxsubjecttableDao iPxsubjecttableDao;
    @Override
    public List<Pxsubjecttable> getNoTeakemu(QueryWrapper queryWrapper) {
        return iPxsubjecttableDao.getNoTeakemu(queryWrapper);
    }
}
