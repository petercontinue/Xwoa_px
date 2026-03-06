package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.WscGoods;
import com.xwcloud.cloud.sys.Dao.IWscGoodsDao;
import com.xwcloud.cloud.sys.Service.IWscGoodsService;
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
 * @since 2021-07-29
 */
@Service
public class WscGoodsServiceImpl extends ServiceImpl<IWscGoodsDao, WscGoods> implements IWscGoodsService {
    @Autowired
    IWscGoodsDao iWscGoodsDao;

    @Override
    public List<HashMap<String, Object>> getnoKCGoods(QueryWrapper queryWrapper) {
        return iWscGoodsDao.getnoKCGoods(queryWrapper);
    }
}
