package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstugradetable;
import com.xwcloud.cloud.stu.Dao.IPxstugradetableDao;
import com.xwcloud.cloud.stu.Service.IPxstugradetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-03
 */
@Service
public class PxstugradetableServiceImpl extends ServiceImpl<IPxstugradetableDao, Pxstugradetable> implements IPxstugradetableService {
    @Autowired
    IPxstugradetableDao iPxstugradetableDao;

    @Override
    public Pxstugradetable getOne(Long qiyeID) {
        return iPxstugradetableDao.getOne(qiyeID);
    }

    @Override
    public List<Pxstugradetable> selectstuGrade(QueryWrapper queryWrapper) {
        return iPxstugradetableDao.selectstuGrade(queryWrapper);
    }
}
