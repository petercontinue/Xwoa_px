package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxscoretable;

import com.xwcloud.cloud.stu.Dao.IPxscoretableDao;
import com.xwcloud.cloud.stu.Service.IPxscoretableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Service
public class PxscoretableServiceImpl extends ServiceImpl<IPxscoretableDao, Pxscoretable> implements IPxscoretableService {
	@Autowired
    IPxscoretableDao iPxscoretableDao;

    @Override
    public List<Pxscoretable> getTestT(String testTitle,Long qiyeID) {
        return iPxscoretableDao.getTestT(testTitle,qiyeID);
    }
}
