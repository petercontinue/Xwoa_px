package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.WscPingtuanFaqiRecordVo;
import com.xwcloud.cloud.model.entity.WscPingtuanFaqirecord;
import com.xwcloud.cloud.wsc.Dao.IWscPingtuanFaqirecordDao;
import com.xwcloud.cloud.wsc.Service.IWscPingtuanFaqirecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Service
public class WscPingtuanFaqirecordServiceImpl extends ServiceImpl<IWscPingtuanFaqirecordDao, WscPingtuanFaqirecord> implements IWscPingtuanFaqirecordService {

    @Autowired
    private IWscPingtuanFaqirecordDao wscPingtuanFaqirecordDao;

    @Override
    public Page<WscPingtuanFaqiRecordVo> getWscPingtuanFaqiPage(Page page, QueryWrapper queryWrapper) {
        return wscPingtuanFaqirecordDao.getWscPingtuanFaqiPage(page, queryWrapper);
    }

    @Override
    public List<WscPingtuanFaqirecord> GetFaqipingtuanlist(long wscgoodsID) {
        return wscPingtuanFaqirecordDao.GetFaqipingtuanlist(wscgoodsID);
    }

    @Override
    public List<WscPingtuanFaqirecord> GetFaqipingtuanSuccesslist(long wscgoodsID) {
        return wscPingtuanFaqirecordDao.GetFaqipingtuanSuccesslist(wscgoodsID);
    }
}
