package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxoldschooltable;
import com.xwcloud.cloud.stu.Dao.IPxoldschooltableDao;
import com.xwcloud.cloud.stu.Service.IPxoldschooltableService;
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
public class PxoldschooltableServiceImpl extends ServiceImpl<IPxoldschooltableDao, Pxoldschooltable> implements IPxoldschooltableService {
    @Autowired
    IPxoldschooltableDao iPxoldschooltableDao;

    @Override
    public List<Pxoldschooltable> getoldschool(String schoolName, Long qiyeID) {
        return iPxoldschooltableDao.getoldschool(schoolName, qiyeID);
    }
}
