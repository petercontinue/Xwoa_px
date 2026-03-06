package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxoldschooltable;
import com.xwcloud.cloud.zsbm.Dao.IPxoldschooltableDao;
import com.xwcloud.cloud.zsbm.Service.IPxoldschooltableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
@Service
public class PxoldschooltableServiceImpl extends ServiceImpl<IPxoldschooltableDao, Pxoldschooltable> implements IPxoldschooltableService {
    @Autowired
    IPxoldschooltableDao iPxoldschooltableDao;

    @Override
    public Pxoldschooltable GetOldSchoolByName(String oldSchoolName) {
        return iPxoldschooltableDao.GetOldSchoolByName(oldSchoolName);
    }
}
