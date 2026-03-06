package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxstutableDao;
import com.xwcloud.cloud.homeschool.Dao.IPxwxusertableDao;
import com.xwcloud.cloud.homeschool.Service.IPxwxusertableService;

import com.xwcloud.cloud.model.Vo.PxwxusertableVo;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.model.entity.Pxwxusertable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
@Service
public class PxwxusertableServiceImpl extends ServiceImpl<IPxwxusertableDao, Pxwxusertable> implements IPxwxusertableService {

    @Autowired
    IPxstutableDao iPxstutableDao;

    @Override
    public Page<PxwxusertableVo> getPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getPage(page, wrapper);
    }

    @Override
    public List<PxwxusertableVo> getJoinList(Wrapper wrapper) {
        return this.baseMapper.getJoinList(wrapper);
    }

    @Override
    public Pxstutable getStuById(long id, long qiyeID) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        queryWrapper.eq("qiyeID", qiyeID);
        return iPxstutableDao.selectOne(queryWrapper);
    }

    @Override
    public boolean changeActivity(Pxstutable stu) {
        return iPxstutableDao.updateById(stu) > 0;
    }

    @Override
    public Integer UpdateStuParentTel(long id, String newTel) {
        return iPxstutableDao.UpdateStuParentTel(id, newTel);
    }

    @Override
    public Integer UpdateStuWechatPassword(String newpasswd, long stuId, long qiyeID) {
        return iPxstutableDao.UpdateStuWechatPassword(newpasswd, stuId, qiyeID);
    }

    @Override
    public Integer DeleteWxuser(String tel, long qiyeID) {
        return iPxstutableDao.DeleteWxuser(tel, qiyeID);
    }
}
