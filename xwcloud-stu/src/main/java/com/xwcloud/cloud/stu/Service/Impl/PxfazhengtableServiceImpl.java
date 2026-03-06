package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxfazhengtable;

import com.xwcloud.cloud.stu.Dao.IPxfazhengtableDao;
import com.xwcloud.cloud.stu.Service.IPxfazhengtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-02-26
 */
@Service
public class PxfazhengtableServiceImpl extends ServiceImpl<IPxfazhengtableDao, Pxfazhengtable> implements IPxfazhengtableService {
    @Autowired
    IPxfazhengtableDao iPxfazhengtableDao;

    @Override
    public List<Pxfazhengtable> getstufzList(Long stuID, Long Zsid, Long qiyeID) {
        return iPxfazhengtableDao.getstufzList(stuID, Zsid, qiyeID);
    }
}
