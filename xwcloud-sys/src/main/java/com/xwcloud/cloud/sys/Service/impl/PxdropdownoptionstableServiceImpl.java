package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxdropdownoptionstable;
import com.xwcloud.cloud.sys.Dao.IPxdropdownoptionstableDao;
import com.xwcloud.cloud.sys.Service.IPxdropdownoptionstableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-05
 */
@Service
//@DS("#header.DBname")
public class PxdropdownoptionstableServiceImpl extends ServiceImpl<IPxdropdownoptionstableDao, Pxdropdownoptionstable> implements IPxdropdownoptionstableService {

    @Autowired
    IPxdropdownoptionstableDao iPxdropdownoptionstableDao;

    @Override
    public List<Pxdropdownoptionstable> GetOptionsById(String stuParamTypeId) {
        return iPxdropdownoptionstableDao.GetOptionsById(stuParamTypeId);
    }
}
