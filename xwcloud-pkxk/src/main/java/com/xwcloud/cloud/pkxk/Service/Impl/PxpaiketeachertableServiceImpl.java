package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxpaiketeachertable;
import com.xwcloud.cloud.pkxk.Dao.IPxpaiketeachertableDao;
import com.xwcloud.cloud.pkxk.Service.IPxpaiketeachertableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
@Service
public class PxpaiketeachertableServiceImpl extends ServiceImpl<IPxpaiketeachertableDao, Pxpaiketeachertable> implements IPxpaiketeachertableService {
	@Autowired
    IPxpaiketeachertableDao iPxpaiketeachertableDao;

    @Override
    public List<Pxpaiketeachertable> selectkehaoTeacher(QueryWrapper queryWrapper) {
        return iPxpaiketeachertableDao.selectkehaoTeacher(queryWrapper);
    }
}
