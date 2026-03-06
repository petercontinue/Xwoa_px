package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxchongzhitable;
import com.xwcloud.cloud.stu.Dao.IPxchongzhitableDao;
import com.xwcloud.cloud.stu.Service.IPxchongzhitableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
@Service
public class PxchongzhitableServiceImpl extends ServiceImpl<IPxchongzhitableDao, Pxchongzhitable> implements IPxchongzhitableService {
	@Autowired
    IPxchongzhitableDao iPxchongzhitableDao;

    @Override
    public List<Pxchongzhitable> getstucz(Long stuID,Long qiyeID) {
        return iPxchongzhitableDao.getstucz(stuID,qiyeID);
    }
}
