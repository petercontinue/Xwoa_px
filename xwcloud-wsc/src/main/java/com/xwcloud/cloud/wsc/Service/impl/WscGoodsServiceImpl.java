package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.WscGoodsVo;
import com.xwcloud.cloud.model.entity.WscGoods;
import com.xwcloud.cloud.model.entity.WscGoodsshuxinglistpricePingtuan;
import com.xwcloud.cloud.wsc.Dao.IWscGoodsDao;
import com.xwcloud.cloud.wsc.Service.IWscGoodsService;
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
public class WscGoodsServiceImpl extends ServiceImpl<IWscGoodsDao, WscGoods> implements IWscGoodsService {

    @Autowired
    private IWscGoodsDao wscGoodsDao;

    @Override
    public Page<WscGoodsVo> getWscGoodsPage(Page page, QueryWrapper wrapper) {
        return wscGoodsDao.getWscGoodsPage(page, wrapper);
    }

    @Override
    public List<WscGoodsVo> GetAllGoodsListByHuodongID(QueryWrapper wrapper) {
        return wscGoodsDao.GetAllGoodsListByHuodongID(wrapper);
    }

    @Override
    public List<WscGoodsshuxinglistpricePingtuan> getshuxinglistpingtuanPricebygoodsID(long goodsID) {
        return wscGoodsDao.getshuxinglistpingtuanPricebygoodsID(goodsID);
    }

    @Override
    public List<HashMap<String, Object>> GetGoodsDetail(String goodsID) {
        return wscGoodsDao.GetGoodsDetail(goodsID);
    }
}
