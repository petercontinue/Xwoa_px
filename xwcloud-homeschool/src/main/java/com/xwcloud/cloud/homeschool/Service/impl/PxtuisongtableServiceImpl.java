package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxtuisongtableDao;
import com.xwcloud.cloud.homeschool.Service.IPxtuisongtableService;

import com.xwcloud.cloud.model.Vo.classInfoListVO;
import com.xwcloud.cloud.model.Vo.exporttuisongVO;
import com.xwcloud.cloud.model.Vo.wchatmessageVO;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.model.entity.Pxtuisongtable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-06
 */
@Service
public class PxtuisongtableServiceImpl extends ServiceImpl<IPxtuisongtableDao, Pxtuisongtable> implements IPxtuisongtableService {

    @Autowired
    IPxtuisongtableDao iPxtuisongtableDao;

    @Override
    public List<Pxstutable> getStuList(String IDs, long qiyeID) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        queryWrapper.in("id", IDs);
        return this.baseMapper.getStuList(queryWrapper);
    }

    @Override
    public List<Pxstutable> getStuByClass(String classId, long qiyeID) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("stuclass.qiyeID", qiyeID);
        queryWrapper.in("stuclass.classID", classId);
        return this.baseMapper.getStuByClassList(queryWrapper);
    }

    @Override
    public List<Pxstutable> getStuByCampus(String campusIds, long qiyeID) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        queryWrapper.in("campusID", campusIds);
        return this.baseMapper.getStuList(queryWrapper);
    }

    @Override
    public Page<wchatmessageVO> GetStuWechatMessagePages(Page page, QueryWrapper wrapper) {
        return iPxtuisongtableDao.GetStuWechatMessagePages(page, wrapper);
    }

    @Override
    public List<searchVO> getTuisongTypeList() {
        return iPxtuisongtableDao.getTuisongTypeList();
    }

    @Override
    public List<exporttuisongVO> SearchTuisongMessageList(QueryWrapper wrapper) {
        return iPxtuisongtableDao.SearchTuisongMessageList(wrapper);
    }

    @Override
    public List<classInfoListVO> GetAllClassListInfo(QueryWrapper wrapper) {
        return iPxtuisongtableDao.GetAllClassListInfo(wrapper);
    }
}
