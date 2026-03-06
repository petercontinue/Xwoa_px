package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.WscOrderVo;
import com.xwcloud.cloud.model.Vo.miaoshachenggongVO;
import com.xwcloud.cloud.model.entity.WscOrder;
import com.xwcloud.cloud.wsc.Dao.IWscOrderDao;
import com.xwcloud.cloud.wsc.Service.IWscOrderService;
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
 * @since 2021-01-17
 */
@Service
public class WscOrderServiceImpl extends ServiceImpl<IWscOrderDao, WscOrder> implements IWscOrderService {

    @Autowired
    IWscOrderDao wscOrderDao;

    @Override
    public Page<WscOrderVo> getWscOrderPage(Page page, QueryWrapper wrapper) {
        return wscOrderDao.getWscOrderPage(page, wrapper);
    }

    @Override
    public List<HashMap<String, Object>> getWscOrderList(QueryWrapper wrapper) {
        return wscOrderDao.getWscOrderList(wrapper);
    }

    @Override
    public Page<HashMap<String, Object>> GetsqleLiushuiPages(Page page, QueryWrapper wrapper) {
        return wscOrderDao.GetsqleLiushuiPages(page, wrapper);
    }

    @Override
    public Page<HashMap<String, Object>> GetorderByUser(Page page, QueryWrapper wrapper) {
        return this.baseMapper.GetorderByUser(page, wrapper);
    }

    @Override
    public List<miaoshachenggongVO> GetmiaoshachenggongInfo(long qiyeID, long goodsID) {
        return this.baseMapper.GetmiaoshachenggongInfo(qiyeID, goodsID);
    }
}
