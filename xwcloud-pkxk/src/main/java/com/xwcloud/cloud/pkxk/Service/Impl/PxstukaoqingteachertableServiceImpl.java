package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstukaoqingteachertable;

import com.xwcloud.cloud.pkxk.Dao.IPxstukaoqingteachertableDao;
import com.xwcloud.cloud.pkxk.Service.IPxstukaoqingteachertableService;
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
public class PxstukaoqingteachertableServiceImpl extends ServiceImpl<IPxstukaoqingteachertableDao, Pxstukaoqingteachertable> implements IPxstukaoqingteachertableService {
	@Autowired
    IPxstukaoqingteachertableDao iPxstukaoqingteachertableDao;

    @Override
    public List<Pxstukaoqingteachertable> selectstukaoteacherqing(QueryWrapper queryWrapper) {
        return iPxstukaoqingteachertableDao.selectstukaoteacherqing(queryWrapper);
    }
}
