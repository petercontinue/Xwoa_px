package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstugradetable;
import com.xwcloud.cloud.zsbm.Dao.IPxstugradetableDao;
import com.xwcloud.cloud.zsbm.Service.IPxstugradetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-08
 */
@Service
public class PxstugradetableServiceImpl extends ServiceImpl<IPxstugradetableDao, Pxstugradetable> implements IPxstugradetableService {

    @Autowired
    IPxstugradetableDao iPxstugradetableDao;

    @Override
    public Pxstugradetable getOne(Long qiyeID) {
        return iPxstugradetableDao.getOne(qiyeID);
    }
}
