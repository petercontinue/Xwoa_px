package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxbuxistyletable;
import com.xwcloud.cloud.stu.Dao.IPxbuxistyletableDao;
import com.xwcloud.cloud.stu.Service.IPxbuxistyletableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-20
 */
@Service
public class PxbuxistyletableServiceImpl extends ServiceImpl<IPxbuxistyletableDao, Pxbuxistyletable> implements IPxbuxistyletableService {
    @Autowired
    IPxbuxistyletableDao iPxbuxistyletableDao;

    @Override
    public Pxbuxistyletable getOne(Long qiyeID) {
        return iPxbuxistyletableDao.getOne(qiyeID);
    }

    @Override
    public List<Pxbuxistyletable> selectbxstyle(QueryWrapper queryWrapper) {
        return iPxbuxistyletableDao.selectbxstyle(queryWrapper);
    }

    @Override
    public List<listVo> getAllbuxiStyleList(Long qiyeID) {
        return iPxbuxistyletableDao.getAllbuxiStyleList(qiyeID);
    }
}
