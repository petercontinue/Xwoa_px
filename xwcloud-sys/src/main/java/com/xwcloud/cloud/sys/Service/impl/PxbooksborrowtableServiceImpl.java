package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxbooksborrowtable;
import com.xwcloud.cloud.sys.Dao.IPxbooksborrowtableDao;
import com.xwcloud.cloud.sys.Service.IPxbooksborrowtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-30
 */
@Service
public class PxbooksborrowtableServiceImpl extends ServiceImpl<IPxbooksborrowtableDao, Pxbooksborrowtable> implements IPxbooksborrowtableService {

    @Autowired
    IPxbooksborrowtableDao iPxbooksborrowtableDao;

    @Override
    public String getnobreakList(QueryWrapper queryWrapper) {
        return iPxbooksborrowtableDao.getnobreakList(queryWrapper);
    }
}
