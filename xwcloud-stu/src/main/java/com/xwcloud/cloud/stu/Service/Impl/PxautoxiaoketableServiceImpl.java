package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxautoxiaoketable;
import com.xwcloud.cloud.stu.Dao.IPxautoxiaoketableDao;
import com.xwcloud.cloud.stu.Service.IPxautoxiaoketableService;
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
public class PxautoxiaoketableServiceImpl extends ServiceImpl<IPxautoxiaoketableDao, Pxautoxiaoketable> implements IPxautoxiaoketableService {
    @Autowired
    IPxautoxiaoketableDao iPxautoxiaoketableDao;

    @Override
    public List<Pxautoxiaoketable> getbyauto(Long buxiID, Long qiyeID) {
        return iPxautoxiaoketableDao.getbyauto(buxiID, qiyeID);
    }
}
