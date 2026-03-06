package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.WscOrderGoodsVo;
import com.xwcloud.cloud.model.entity.WscOrdergoods;
import com.xwcloud.cloud.wsc.Dao.IWscOrdergoodsDao;
import com.xwcloud.cloud.wsc.Service.IWscOrdergoodsService;
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
public class WscOrdergoodsServiceImpl extends ServiceImpl<IWscOrdergoodsDao, WscOrdergoods> implements IWscOrdergoodsService {
    @Autowired
    IWscOrdergoodsDao iWscOrdergoodsDao;

    @Override
    public Page<HashMap<String, Object>> getOrderGoodsDetailByOrderNumberPage(Page page, QueryWrapper wrapper) {
        return iWscOrdergoodsDao.getOrderGoodsDetailByOrderNumberPage(page, wrapper);
    }

    @Override
    public Page<HashMap<String, Object>> GetAllOrderPingjiaPage(Page page, QueryWrapper wrapper) {
        return iWscOrdergoodsDao.GetAllOrderPingjiaPage(page, wrapper);
    }

    @Override
    public List<HashMap<String, Object>> getonegoodpingjia(QueryWrapper queryWrapper) {
        return iWscOrdergoodsDao.getonegoodpingjia(queryWrapper);
    }

    @Override
    public Page<WscOrderGoodsVo> getWscOrderGoodsPage(Page page, QueryWrapper wrapper) {
        return iWscOrdergoodsDao.getWscOrderGoodsPage(page, wrapper);
    }
}
