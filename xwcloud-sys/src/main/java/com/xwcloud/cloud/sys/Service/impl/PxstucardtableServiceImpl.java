package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstucardtable;
import com.xwcloud.cloud.sys.Dao.IPxstucardtableDao;
import com.xwcloud.cloud.sys.Service.IPxstucardtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-29
 */
@Service
public class PxstucardtableServiceImpl extends ServiceImpl<IPxstucardtableDao, Pxstucardtable> implements IPxstucardtableService {
    @Autowired
    IPxstucardtableDao iPxstucardtableDao;

    @Override
    public List<Pxstucardtable> getNostuCardlist(QueryWrapper queryWrapper) {
        return iPxstucardtableDao.getNostuCardlist(queryWrapper);
    }
}
