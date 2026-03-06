package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.wsc.Dao.IWscDongtaiinfoDao;
import com.xwcloud.cloud.wsc.Service.IWscDongtaiinfoService;

import com.xwcloud.cloud.model.Vo.dongtaiVO;
import com.xwcloud.cloud.model.Vo.dongtaiinfoVO;
import com.xwcloud.cloud.model.entity.WscDongtaiinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-18
 */
@Service
public class WscDongtaiinfoServiceImpl extends ServiceImpl<IWscDongtaiinfoDao, WscDongtaiinfo> implements IWscDongtaiinfoService {

    @Autowired
    IWscDongtaiinfoDao iWscDongtaiinfoDao;

    @Override
    public List<dongtaiVO> GetIndexDongtaifenxiang(long wscuserID) {
        return iWscDongtaiinfoDao.GetIndexDongtaifenxiang(wscuserID);
    }

    @Override
    public List<dongtaiinfoVO> GetDongtaiInfo(long dongtaiID, long wscuserID) {
        return iWscDongtaiinfoDao.GetDongtaiInfo(dongtaiID, wscuserID);
    }

    @Override
    public Page<dongtaiVO> GetMyDongtaiInfo(Page page, long wscuserID, QueryWrapper queryWrapper) {
        return iWscDongtaiinfoDao.GetMyDongtaiInfo(page, wscuserID, queryWrapper);
    }
}
