package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.IPxkeshistutableDao;
import com.xwcloud.cloud.caiwu.Service.IPxkeshistutableService;
import com.xwcloud.cloud.model.Vo.LiushilvStuVo;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-15
 */
@Service
public class PxkeshistutableServiceImpl extends ServiceImpl<IPxkeshistutableDao, Pxkeshistutable> implements IPxkeshistutableService {

    @Override
    public Page<HashMap<String, Object>> getNianjiPage(Page page, Wrapper wrapper) {
        Page page1 = this.baseMapper.getNainjiPage(page, wrapper);
        String NianjiSum = this.baseMapper.getBanjiSum(wrapper);
        page1.setCountId(NianjiSum);
        return page1;
    }

    @Override
    public Page<HashMap<String, Object>> getBanjiPage(Page page, Wrapper wrapper) {
        Page page1 = this.baseMapper.getBanjiPage(page, wrapper);
        String BanjiSum = this.baseMapper.getBanjiSum(wrapper);
        page1.setCountId(BanjiSum);
        return page1;
    }

    @Override
    public Page<HashMap<String, Object>> getBuxiStylePage(Page page, Wrapper wrapper) {
        Page page1 = this.baseMapper.getBuxiStylePage(page, wrapper);
        String BuxiStyleSum = this.baseMapper.getBuxiStyleSum(wrapper);
        page1.setCountId(BuxiStyleSum);
        return page1;
    }

    @Override
    public Page<HashMap<String, Object>> getKemuPage(Page page, Wrapper wrapper) {
        Page page1 = this.baseMapper.getKemuPage(page, wrapper);
        String kemuSum = this.baseMapper.getKemuSum(wrapper);
        page1.setCountId(kemuSum);
        return page1;
    }

    @Override
    public Page<HashMap<String, String>> getBanzhurenPage(Page page, Wrapper wrapper) {
        String banzhurenSum = this.baseMapper.getBanzhurenSum(wrapper);
        Page page1 = this.baseMapper.getBanzhurenPage(page, wrapper);
        page1.setCountId(banzhurenSum);
        return page1;
    }

    @Override
    public Page<HashMap<String, String>> getYuejunkehaoPage(Page page, Wrapper wrapper) {
        Page page1 = this.baseMapper.getYuejunkehaoPage(page, wrapper);
        String shouruSum = this.baseMapper.getYuejunkehaoSum(wrapper);
        page1.setCountId(shouruSum);
        return page1;
    }

    @Override
    public Page<HashMap<String, Object>> getJiaoshiyuejunkehaoPage(Page page, Wrapper wrapper) {
        Page page1 = this.baseMapper.getJiaoshiyuejunkehaoPage(page, wrapper);
//        String yuejunkehaoSum = this.baseMapper.getJiaoshiyuejunkehaoSum(wrapper);
//        page1.setCountId(yuejunkehaoSum);
        return page1;
    }

    @Override
    public List<HashMap<String, String>> getKehaotongbihuanbi(long campusID, Integer startDate, Integer endDate, long qiyeID) {
        return this.baseMapper.getKehaotongbihuanbi(campusID, startDate, endDate, qiyeID);
    }

    @Override
    public Page<HashMap<String, Object>> getXuesherenshu(Page page, QueryWrapper queryWrapper, QueryWrapper queryWrapper2) {
        return this.baseMapper.getXuesherenshu(page, queryWrapper, queryWrapper2);
    }

    @Override
    public List<HashMap<String, Object>> exportxueyuanrenshu(QueryWrapper queryWrapper, QueryWrapper queryWrapper2) {
        return this.baseMapper.exportxueyuanrenshu(queryWrapper, queryWrapper2);
    }

    @Override
    public Page<HashMap<String, String>> getTeacherStu(Page page, long campusID, String teacherName, long qiyeID) {
        return this.baseMapper.getTeacherStu(page, campusID, teacherName, qiyeID);
    }

    @Override
    public Page<HashMap<String, String>> getTeacherStuDetaile(Page page, long campusID, long teacherID, long qiyeID) {
        return this.baseMapper.getTeacherStuDetaile(page, campusID, teacherID, qiyeID);
    }

    @Override
    public Page<HashMap<String, String>> getBanzhurenStu(Page page, long campusID, String teacherName, long qiyeID) {
        return this.baseMapper.getBanzhurenStu(page, campusID, teacherName, qiyeID);
    }

    @Override
    public Page<HashMap<String, String>> getBanzhurenStuDetaile(Page page, long campusID, long teacherID, long qiyeID) {
        return this.baseMapper.getBanzhurenStuDetaile(page, campusID, teacherID, qiyeID);
    }

    @Override
    public List<HashMap<String, Object>> getBanzhurenList(Wrapper wrapper) {
        return this.baseMapper.getBanzhurenList(wrapper);
    }

    @Override
    public Page<LiushilvStuVo> getTeaStuLiushiPage(Page page, Wrapper wrapper,QueryWrapper queryWrapper2) {
        return this.baseMapper.getTeaStuLiushiPage(page, wrapper,queryWrapper2);
    }

    @Override
    public Page<LiushilvStuVo> getBanzhurenStuLiushiPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getBanzhurenStuLiushiPage(page, wrapper);
    }

    @Override
    public Page<LiushilvStuVo> getCampusStuLiushiPage(Page page, Wrapper wrapper, int yue, QueryWrapper queryWrapper) {
        return this.baseMapper.getCampusStuLiushiPage(page, wrapper, yue, queryWrapper);
    }

//    @Override
//    public Page<LiushilvStuVo> getCampusStuLiushiPage(Page page, Wrapper wrapper) {
//        return this.baseMapper.getCampusStuLiushiPage(page, wrapper);
//    }

    @Override
    public Page<HashMap<String, Object>> getClassProfit(Page page, QueryWrapper wrapper) {
        return this.baseMapper.getClassProfit(page, wrapper);
    }
}
