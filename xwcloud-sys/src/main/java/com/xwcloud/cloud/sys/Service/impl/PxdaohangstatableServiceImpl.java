package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxdaohangstatable;
import com.xwcloud.cloud.sys.Dao.IPxdaohangstatableDao;
import com.xwcloud.cloud.sys.Service.IPxdaohangstatableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-30
 */
@Service
public class PxdaohangstatableServiceImpl extends ServiceImpl<IPxdaohangstatableDao, Pxdaohangstatable> implements IPxdaohangstatableService {

    @Autowired
    IPxdaohangstatableDao iPxdaohangstatableDao;

    @Override
    public List<HashMap<String, Object>> getstaffdaohang(QueryWrapper queryWrapper) {
        return iPxdaohangstatableDao.getstaffdaohang(queryWrapper);
    }
}
