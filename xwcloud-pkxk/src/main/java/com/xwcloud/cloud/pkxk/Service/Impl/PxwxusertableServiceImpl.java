package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxwxusertable;
import com.xwcloud.cloud.pkxk.Dao.IPxwxusertableDao;
import com.xwcloud.cloud.pkxk.Service.IPxwxusertableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-14
 */
@Service
public class PxwxusertableServiceImpl extends ServiceImpl<IPxwxusertableDao, Pxwxusertable> implements IPxwxusertableService {
    @Autowired
    IPxwxusertableDao iPxwxusertableDao;

    @Override
    public List<Pxwxusertable> getuserList(Long stuID, Long qiyeID) {
        return iPxwxusertableDao.getuserList(stuID, qiyeID);
    }
}
