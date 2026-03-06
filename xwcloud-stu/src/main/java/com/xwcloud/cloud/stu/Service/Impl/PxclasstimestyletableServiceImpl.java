package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxclasstimestyletable;
import com.xwcloud.cloud.stu.Dao.IPxclasstimestyletableDao;
import com.xwcloud.cloud.stu.Service.IPxclasstimestyletableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-20
 */
@Service
public class PxclasstimestyletableServiceImpl extends ServiceImpl<IPxclasstimestyletableDao, Pxclasstimestyletable> implements IPxclasstimestyletableService {
    @Autowired
    IPxclasstimestyletableDao iPxclasstimestyletableDao;

    @Override
    public List<Pxclasstimestyletable> selectclassTime(QueryWrapper queryWrapper) {
        return iPxclasstimestyletableDao.selectclassTime(queryWrapper);
    }

    @Override
    public List<listVo> getAllClassTimeList(Long qiyeID) {
        return iPxclasstimestyletableDao.getAllClassTimeList(qiyeID);
    }
}
