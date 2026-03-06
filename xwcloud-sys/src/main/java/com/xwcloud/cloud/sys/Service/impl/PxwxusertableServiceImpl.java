package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxwxusertable;
import com.xwcloud.cloud.sys.Dao.IPxwxusertableDao;
import com.xwcloud.cloud.sys.Service.IPxwxusertableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-05
 */
@Service
//@DS("#header.DBname")
public class PxwxusertableServiceImpl extends ServiceImpl<IPxwxusertableDao, Pxwxusertable> implements IPxwxusertableService {

    @Autowired
    IPxwxusertableDao iPxwxusertableDao;

    @Override
    public Pxwxusertable GetWxuserByStaffID(String staffID) {
        return iPxwxusertableDao.GetWxuserByStaffID(staffID);
    }
}
