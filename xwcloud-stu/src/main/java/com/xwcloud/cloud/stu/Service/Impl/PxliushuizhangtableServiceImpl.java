package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxliushuizhangtable;
import com.xwcloud.cloud.stu.Dao.IPxliushuizhangtableDao;
import com.xwcloud.cloud.stu.Service.IPxliushuizhangtableService;
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
public class PxliushuizhangtableServiceImpl extends ServiceImpl<IPxliushuizhangtableDao, Pxliushuizhangtable> implements IPxliushuizhangtableService {
	@Autowired
    IPxliushuizhangtableDao iPxliushuizhangtableDao;

    @Override
    public List<Pxliushuizhangtable> getstuliushui(Long stuID,Long qiyeID) {
        return iPxliushuizhangtableDao.getstuliushui(stuID,qiyeID);
    }
}
