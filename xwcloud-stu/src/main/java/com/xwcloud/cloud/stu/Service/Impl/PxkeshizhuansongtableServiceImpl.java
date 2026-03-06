package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxkeshizhuansongtable;

import com.xwcloud.cloud.stu.Dao.IPxkeshizhuansongtableDao;
import com.xwcloud.cloud.stu.Service.IPxkeshizhuansongtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-22
 */
@Service
public class PxkeshizhuansongtableServiceImpl extends ServiceImpl<IPxkeshizhuansongtableDao, Pxkeshizhuansongtable> implements IPxkeshizhuansongtableService {

    @Autowired
    IPxkeshizhuansongtableDao iPxkeshizhuansongtableDao;

    @Override
    public List<Pxkeshizhuansongtable> getksZhuangSong(Long qiyeID,Long stuID) {
        return iPxkeshizhuansongtableDao.getksZhuangSong(qiyeID,stuID);
    }
}
