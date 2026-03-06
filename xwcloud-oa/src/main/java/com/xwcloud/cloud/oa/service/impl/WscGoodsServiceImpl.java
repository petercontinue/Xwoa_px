package com.xwcloud.cloud.oa.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.WscGoods;
import com.xwcloud.cloud.oa.Dao.IWscGoodsDao;
import com.xwcloud.cloud.oa.service.IWscGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-25
 */
@Service
public class WscGoodsServiceImpl extends ServiceImpl<IWscGoodsDao, WscGoods> implements IWscGoodsService {
    @Autowired
    IWscGoodsDao iWscGoodsDao;


    @Override
    public List<WscGoods> updategoodscampus(String cxStr, Long id) {
        return iWscGoodsDao.updategoodscampus(cxStr, id);
    }
}
