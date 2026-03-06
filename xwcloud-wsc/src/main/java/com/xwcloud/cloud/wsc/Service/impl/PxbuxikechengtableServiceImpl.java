package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;
import com.xwcloud.cloud.wsc.Dao.IPxbuxikechengtableDao;
import com.xwcloud.cloud.wsc.Service.IPxbuxikechengtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-31
 */
@Service
public class PxbuxikechengtableServiceImpl extends ServiceImpl<IPxbuxikechengtableDao, Pxbuxikechengtable> implements IPxbuxikechengtableService {
    @Autowired
    IPxbuxikechengtableDao iPxbuxikechengtableDao;

    @Override
    public String getstuInClass(String stuID, Long qiyeID) {
        return iPxbuxikechengtableDao.getstuInClass(stuID, qiyeID);
    }
}
