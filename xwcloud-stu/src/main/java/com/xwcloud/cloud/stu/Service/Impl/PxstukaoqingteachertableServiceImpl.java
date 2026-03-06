package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstukaoqingteachertable;
import com.xwcloud.cloud.stu.Dao.IPxstukaoqingteachertableDao;
import com.xwcloud.cloud.stu.Service.IPxstukaoqingteachertableService;
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
public class PxstukaoqingteachertableServiceImpl extends ServiceImpl<IPxstukaoqingteachertableDao, Pxstukaoqingteachertable> implements IPxstukaoqingteachertableService {
    @Autowired
    IPxstukaoqingteachertableDao iPxstukaoqingteachertableDao;

    @Override
    public List<Pxstukaoqingteachertable> getkqTeach(Long stukaoqingTabID, Long qiyeID) {
        return iPxstukaoqingteachertableDao.getkqTeach(stukaoqingTabID, qiyeID);
    }
}
