package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.IPxtuifeitableDao;
import com.xwcloud.cloud.caiwu.Service.IPxtuifeitableService;
import com.xwcloud.cloud.model.Vo.PxtuifeitableVo;
import com.xwcloud.cloud.model.Vo.alltuizfVo;
import com.xwcloud.cloud.model.entity.Pxtuifeitable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
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
public class PxtuifeitableServiceImpl extends ServiceImpl<IPxtuifeitableDao, Pxtuifeitable> implements IPxtuifeitableService {

    @Autowired
    IPxtuifeitableDao iPxtuifeitableDao;

    @Override
    public Page<PxtuifeitableVo> getPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getPage(page, wrapper);
    }

    @Override
    public List<PxtuifeitableVo> getJoinList(Wrapper wrapper) {
        return this.baseMapper.getJoinList(wrapper);
    }

    //region 课程数据
    @Override
    public String getSumKechengFee(QueryWrapper wrapper, String qiyeID) {
        wrapper.eq("qdsp.qiyeID", qiyeID);
        return this.baseMapper.getSumKechengFee(wrapper);
    }

    @Override
    public String getKeshiXiaohaoSum(QueryWrapper wrapper, String qiyeID) {
        wrapper.eq("keshi.qiyeID", qiyeID);
        return this.baseMapper.getKeshiXiaohao(wrapper);
    }

    @Override
    public String getJieshouKeshiSum(QueryWrapper wrapper, String stuID, String qiyeID) {
        wrapper.eq("qdsp.shoustuID", stuID);
        wrapper.eq("qdsp.qiyeID", qiyeID);
        return this.baseMapper.getJieshouKeshiSum(wrapper);
    }

    @Override
    public String getZhuansongKeshiSum(QueryWrapper wrapper, String stuID, String qiyeID) {
        wrapper.eq("qdsp.songstuID", stuID);
        wrapper.eq("qdsp.qiyeID", qiyeID);
        return this.baseMapper.getZhuansongKeshiSum(wrapper);
    }
    //endregion

    //region 充值数据
    @Override
    public String getJiaofei(String stuID, String qiyeID) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qdsp.stuID", stuID);
        queryWrapper.eq("qdsp.qiyeID", qiyeID);
        return this.baseMapper.getJiaofei(queryWrapper);
    }

    @Override
    public String getZengsong(String stuID, String qiyeID) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qdsp.stuID", stuID);
        queryWrapper.eq("qdsp.qiyeID", qiyeID);
        return this.baseMapper.getZengsong(queryWrapper);
    }

    @Override
    public String getXiaohaoFee(String stuID, String qiyeID) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qdsp.stuID", stuID);
        queryWrapper.eq("qdsp.qiyeID", qiyeID);
        return this.baseMapper.getXiaohaoFee(queryWrapper);
    }

    @Override
    public String getYuFee(String stuID, String qiyeID) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", stuID);
        queryWrapper.eq("qiyeID", qiyeID);
        return this.baseMapper.getYuFee(queryWrapper);
    }

    //endregion
    @Override
    public String getCommodityFee(QueryWrapper wrapper, String qiyeID) {
        wrapper.eq("qdsp.qiyeID", qiyeID);
        return this.baseMapper.getCommodityFee(wrapper);
    }

    @Override
    public String getOtherFee(QueryWrapper wrapper, String qiyeID) {
        wrapper.eq("qdsp.qiyeID", qiyeID);
        return this.baseMapper.getOtherFee(wrapper);
    }

    @Override
    public String getDaijinquan(QueryWrapper wrapper, String qiyeID) {
        wrapper.eq("qdsp.qiyeID", qiyeID);
        return this.baseMapper.getDaijinquan(wrapper);
    }

    @Override
    public String getYouhuiFee(QueryWrapper wrapper, String qiyeID) {
        wrapper.eq("qdinfo.qiyeID", qiyeID);
        return this.baseMapper.getYouhuiFee(wrapper);
    }

    @Override
    public List<HashMap<String, String>> getKechengfeiDetail(QueryWrapper wrapper) {
        return this.baseMapper.getKechengfeiDetail(wrapper);
    }

    @Override
    public List<HashMap<String, String>> getChongzhiDetail(QueryWrapper wrapper, Long qiyeID) {
        return this.baseMapper.getChongzhiDetail(wrapper, qiyeID);
    }

    @Override
    public List<HashMap<String, String>> getShangpinDetail(QueryWrapper wrapper) {
        return this.baseMapper.getShangpinDetail(wrapper);
    }

    @Override
    public List<HashMap<String, String>> getZafeiDetail(QueryWrapper wrapper) {
        return this.baseMapper.getZafeiDetail(wrapper);
    }

    @Override
    public List<HashMap<String, String>> getKeshiDetail(QueryWrapper wrapper) {
        return this.baseMapper.getKeshiDetail(wrapper);
    }

    @Override
    public List<HashMap<String, String>> getTransferDetail(QueryWrapper wrapper) {
        return this.baseMapper.getTransferDetail(wrapper);
    }

    @Override
    public List<HashMap<String, String>> getKechengList(QueryWrapper wrapper) {
        return this.baseMapper.getKechengList(wrapper);
    }

    @Override
    public List<HashMap<String, String>> getCommodityList(QueryWrapper wrapper) {
        return this.baseMapper.getCommodityList(wrapper);
    }

    @Override
    public List<HashMap<String, String>> getZafeiList(QueryWrapper wrapper) {
        return this.baseMapper.getZafeiList(wrapper);
    }

    @Override
    public List<alltuizfVo> getalltuizf(Wrapper wrapper) {
        return iPxtuifeitableDao.getalltuizf(wrapper);
    }

}
