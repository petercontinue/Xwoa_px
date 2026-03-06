package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxevaluationtableDao;
import com.xwcloud.cloud.homeschool.Service.IPxevaluationtableService;
import com.xwcloud.cloud.model.Vo.PxevaluationtableVo;
import com.xwcloud.cloud.model.entity.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
@Service
public class PxevaluationtableServiceImpl extends ServiceImpl<IPxevaluationtableDao, Pxevaluationtable> implements IPxevaluationtableService {


    @Override
    public Page<HashMap<String,String>> getPage(Page<HashMap<String,String>> page, QueryWrapper<HashMap<String,String>> queryWrapper) {

        return  this.baseMapper.getPxevaluationtableJoinPage(page,queryWrapper);
    }

    @Override
    public List<PxevaluationtableVo> getJoinList( QueryWrapper<Pxevaluationtable> queryWrapper) {
        return this.baseMapper.getPxevaluationtableJoinList(queryWrapper);
    }

    @Override
    public List<Pxcampustable> getCampusList(Wrapper wrapper) {
        return this.baseMapper.getCampusList(wrapper);
    }

    @Override
    public List<Pxclasstable> getClassList(Wrapper wrapper) {
        return this.baseMapper.getClassList(wrapper);
    }

    @Override
    public List<Pxstutable> getStuList(Wrapper wrapper) {
        return this.baseMapper.getStuList(wrapper);
    }

    @Override
    public List<HashMap<String, String>> getPeriodList(Wrapper wrapper) {
        return this.baseMapper.getPeriodList(wrapper);
    }

    @Override
    public List<Pxstafftable> getStaffList(Wrapper wrapper) {
        return this.baseMapper.getStaffList(wrapper);
    }

    @Override
    public Pxkeshiteachertable getTeacherkeshi(String campusID, String classID, String teacherID, String haveClassDate,
                                               String startLessonDateTime, String endLessonDateTime, Long qiyeID) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("campusID",campusID);
        queryWrapper.eq("classID",classID);
        queryWrapper.eq("teacherID",teacherID);
        queryWrapper.eq("startLessonDateTime",startLessonDateTime);
        queryWrapper.eq("endLessonDateTime",endLessonDateTime);
        queryWrapper.eq("qiyeID",qiyeID);
        queryWrapper.apply("DATE_FORMAT(haveClassDate,'%y-%m-%d') = DATE_FORMAT({0},'%y-%m-%d')",haveClassDate);
        return this.baseMapper.getTeacherkeshi(queryWrapper);
    }

    @Override
    public List<HashMap<String, String>> getPublicStaffList(Wrapper wrapper) {
        return this.baseMapper.getPublicStaffList(wrapper);
    }

    @Override
    public List<HashMap<String, String>> getClassRoomList(Wrapper wrapper) {
        return this.baseMapper.getClassRoomList(wrapper);
    }

    @Override
    public List<HashMap<String, String>> getPublicStuList(Wrapper wrapper) {
        return this.baseMapper.getPublicStuList(wrapper);
    }

    @Override
    public List<HashMap<String, String>> getPublicStaffPostList(Wrapper wrapper) {
        return this.baseMapper.getPublicStaffPostList(wrapper);
    }

    @Override
    public List<HashMap<String, String>> getPublicKemuList(Wrapper wrapper) {
        return this.baseMapper.getPublicKemuList(wrapper);
    }

    @Override
    public List<HashMap<String, String>> getStugradeList(Wrapper wrapper) {
        return this.baseMapper.getStugradeList(wrapper);
    }
}
