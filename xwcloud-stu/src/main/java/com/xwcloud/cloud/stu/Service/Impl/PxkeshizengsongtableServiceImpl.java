package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxkeshizengsongtable;

import com.xwcloud.cloud.stu.Dao.IPxkeshizengsongtableDao;
import com.xwcloud.cloud.stu.Service.IPxkeshizengsongtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
@Service
public class PxkeshizengsongtableServiceImpl extends ServiceImpl<IPxkeshizengsongtableDao, Pxkeshizengsongtable> implements IPxkeshizengsongtableService {
    @Autowired
    IPxkeshizengsongtableDao iPxkeshizengsongtableDao;

    @Override
    public List<Pxkeshizengsongtable> getksZs(Long stuID, Long qiyeID) {
        return iPxkeshizengsongtableDao.getksZs(stuID, qiyeID);
    }
}
