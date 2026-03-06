package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstucardtable;

import com.xwcloud.cloud.pkxk.Dao.IPxstucardtableDao;
import com.xwcloud.cloud.pkxk.Service.IPxstucardtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-19
 */
@Service
public class PxstucardtableServiceImpl extends ServiceImpl<IPxstucardtableDao, Pxstucardtable> implements IPxstucardtableService {
	@Autowired
    IPxstucardtableDao iPxstucardtableDao;

    @Override
    public List<Pxstucardtable> selectstucard(QueryWrapper queryWrapper) {
        return iPxstucardtableDao.selectstucard(queryWrapper);
    }
}
