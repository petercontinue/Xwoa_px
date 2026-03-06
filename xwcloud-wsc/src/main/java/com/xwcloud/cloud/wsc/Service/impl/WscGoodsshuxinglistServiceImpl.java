package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.WscGoodsshuxinglist;
import com.xwcloud.cloud.model.entity.WscGoodsshuxinglistprice;

import com.xwcloud.cloud.model.Vo.WscGoodsAttributeVo;
import com.xwcloud.cloud.model.Vo.guigeshuxingVo;
import com.xwcloud.cloud.wsc.Dao.IWscGoodsshuxinglistDao;
import com.xwcloud.cloud.wsc.Service.IWscGoodsshuxinglistService;
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
public class WscGoodsshuxinglistServiceImpl extends ServiceImpl<IWscGoodsshuxinglistDao, WscGoodsshuxinglist> implements IWscGoodsshuxinglistService {

    @Autowired
    private IWscGoodsshuxinglistDao wscGoodsshuxinglistDao;

    @Override
    public Page<WscGoodsAttributeVo> getGoodsAttributePage(Page page, QueryWrapper wrapper) {
        return wscGoodsshuxinglistDao.getGoodsAttributePage(page, wrapper);
    }

    @Override
    public List<guigeshuxingVo> GetGuigeList(QueryWrapper wrapper) {
        return wscGoodsshuxinglistDao.GetGuigeList(wrapper);
    }

    @Override
    public WscGoodsshuxinglistprice getkucun(Wrapper wrapper) {
        return wscGoodsshuxinglistDao.getkucun(wrapper);
    }

    @Override
    public List<HashMap<String, Object>> GetAllKucun(QueryWrapper wrapper) {
        return wscGoodsshuxinglistDao.GetAllKucun(wrapper);
    }
}
