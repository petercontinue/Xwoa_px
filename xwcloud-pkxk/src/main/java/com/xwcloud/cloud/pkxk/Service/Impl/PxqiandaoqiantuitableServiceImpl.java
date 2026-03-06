package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxqiandaoqiantuitable;

import com.xwcloud.cloud.pkxk.Dao.IPxqiandaoqiantuitableDao;
import com.xwcloud.cloud.pkxk.Service.IPxqiandaoqiantuitableService;
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
 * @since 2020-12-04
 */
@Service
public class PxqiandaoqiantuitableServiceImpl extends ServiceImpl<IPxqiandaoqiantuitableDao, Pxqiandaoqiantuitable> implements IPxqiandaoqiantuitableService {
    @Autowired
    IPxqiandaoqiantuitableDao iPxqiandaoqiantuitableDao;

    @Override
    public List<Pxqiandaoqiantuitable> selectqiaodao(QueryWrapper queryWrapper) {
        return iPxqiandaoqiantuitableDao.selectqiaodao(queryWrapper);
    }

    @Override
    public List<ExportQianCountVo> ExportQianCount(Long qiyeID) {
        return iPxqiandaoqiantuitableDao.ExportQianCount(qiyeID);
    }

    @Override
    public List<ExportQianInfoVo> ExportQianInfo(Long qiyeID) {
        return iPxqiandaoqiantuitableDao.ExportQianInfo(qiyeID);
    }

    @Override
    public Page<ziyouqiandaoVo> getziyouqiandaoPage(Page page, QueryWrapper queryWrapper) {
        return iPxqiandaoqiantuitableDao.getziyouqiandaoPage(page, queryWrapper);
    }

    @Override
    public Page<ziyouqiandaostuNumVo> getziyouqiandaostuNumPage(Page page, Long classID, Long qiyeID) {
        return iPxqiandaoqiantuitableDao.getziyouqiandaostuNumPage(page, classID, qiyeID);
    }

    @Override
    public Page<ziyouqiandaostuNumVo> getziyouqiantuistuNumPage(Page page, Long classID, Long qiyeID) {
        return iPxqiandaoqiantuitableDao.getziyouqiantuistuNumPage(page, classID, qiyeID);
    }

    @Override
    public Page<stuqiaoDaoNumVo> getstuqiaoDaoNumPage(Page page, Long qiyeID, Long stuID) {
        return iPxqiandaoqiantuitableDao.getstuqiaoDaoNumPage(page, qiyeID, stuID);
    }

    @Override
    public Page<stuqiaoDaoNumVo> getstuqiaoTuiNumPage(Page page, Long qiyeID, Long stuID) {
        return iPxqiandaoqiantuitableDao.getstuqiaoTuiNumPage(page, qiyeID, stuID);
    }

    @Override
    public Page<qiandaoLiushuiVo> getqiandaoliushuiPage(Page page, QueryWrapper queryWrapper) {
        return iPxqiandaoqiantuitableDao.getqiandaoliushuiPage(page, queryWrapper);
    }


    @Override
    public Page<shuakaVo> getshuakaPage(Page page, QueryWrapper queryWrapper) {
        return iPxqiandaoqiantuitableDao.getshuakaPage(page, queryWrapper);
    }

    @Override
    public List<shuakaVo> ExportshuakaPage(QueryWrapper queryWrapper) {
        return iPxqiandaoqiantuitableDao.ExportshuakaPage(queryWrapper);
    }

    @Override
    public Page<shuakaxiaokeVo> getshuakaxiaokePage(Page page, QueryWrapper queryWrapper) {
        return iPxqiandaoqiantuitableDao.getshuakaxiaokePage(page, queryWrapper);
    }

    @Override
    public List<shuakaxiaokeVo> Exportshuakaxiaoke(QueryWrapper queryWrapper) {
        return iPxqiandaoqiantuitableDao.Exportshuakaxiaoke(queryWrapper);
    }

    @Override
    public Page<HashMap<String, Object>> GetshualianxiaokeInfoPages(Page page, QueryWrapper queryWrapper) {
        return iPxqiandaoqiantuitableDao.GetshualianxiaokeInfoPages(page,queryWrapper);
    }

}
