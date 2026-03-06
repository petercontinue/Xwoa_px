package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxkechengtable;
import com.xwcloud.cloud.stu.Dao.IPxkechengtableDao;
import com.xwcloud.cloud.stu.Service.IPxkechengtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Service
public class PxkechengtableServiceImpl extends ServiceImpl<IPxkechengtableDao, Pxkechengtable> implements IPxkechengtableService {
    @Autowired
    IPxkechengtableDao iPxkechengtableDao;


    @Override
    public Page<Pxkechengtable> getZSxq(Long ZSid, Long qiyeID, Page page) {
        return iPxkechengtableDao.getZSxq(ZSid, qiyeID, page);
    }

    @Override
    public List<listVo> getKcBySubject(Long subjectID, Long qiyeID) {
        return iPxkechengtableDao.getKcBySubject(subjectID, qiyeID);
    }

    @Override
    public Pxkechengtable getBysubject(Long qiyeID) {
        return iPxkechengtableDao.getBysubject(qiyeID);
    }

    @Override
    public List<Pxkechengtable> selectkc(QueryWrapper queryWrapper) {
        return iPxkechengtableDao.selectkc(queryWrapper);
    }
}
