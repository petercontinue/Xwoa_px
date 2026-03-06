package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxkaojitable;

import com.xwcloud.cloud.stu.Dao.IPxkaojitableDao;
import com.xwcloud.cloud.stu.Service.IPxkaojitableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Service
public class PxkaojitableServiceImpl extends ServiceImpl<IPxkaojitableDao, Pxkaojitable> implements IPxkaojitableService {
    @Autowired
    IPxkaojitableDao iPxkaojitableDao;

    @Override
    public List<Pxkaojitable> getStuASub(Long stuid, Long kemuid, Long qiyeID) {
        return iPxkaojitableDao.getStuASub(stuid, kemuid, qiyeID);
    }
}
