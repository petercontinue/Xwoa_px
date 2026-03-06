package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstukaoqingteachertable;
import com.xwcloud.cloud.zsbm.Dao.IPxstukaoqingteachertableDao;
import com.xwcloud.cloud.zsbm.Service.IPxstukaoqingteachertableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-18
 */
@Service
public class PxstukaoqingteachertableServiceImpl extends ServiceImpl<IPxstukaoqingteachertableDao, Pxstukaoqingteachertable> implements IPxstukaoqingteachertableService {
    @Autowired
    IPxstukaoqingteachertableDao iPxstukaoqingteachertableDao;

    @Override
    public int removeBystukaoqingTabID(Long stukaoqingTabID) {
        return iPxstukaoqingteachertableDao.removeBystukaoqingTabID(stukaoqingTabID);
    }
}
