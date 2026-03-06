package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxpowertable;
import com.xwcloud.cloud.sys.Dao.IPxpowertableDao;
import com.xwcloud.cloud.sys.Service.IPxpowertableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-07
 */
@Service
//@DS("#header.DBname")
public class PxpowertableServiceImpl extends ServiceImpl<IPxpowertableDao, Pxpowertable> implements IPxpowertableService {

    @Autowired
    IPxpowertableDao iPxpowertableDao;

    @Override
    public List<Pxpowertable> GetPowersBystaffpostIDandmenuID(Long menuID, Long staffPostID, long qiyeID) {
        return iPxpowertableDao.GetPowersBystaffpostIDandmenuID(menuID, staffPostID,qiyeID);
    }
}
