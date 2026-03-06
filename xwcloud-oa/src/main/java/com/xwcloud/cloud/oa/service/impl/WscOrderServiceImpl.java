package com.xwcloud.cloud.oa.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.WscOrder;
import com.xwcloud.cloud.oa.Dao.IWscOrderDao;
import com.xwcloud.cloud.oa.service.IWscOrderService;
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
public class WscOrderServiceImpl extends ServiceImpl<IWscOrderDao, WscOrder> implements IWscOrderService {

    @Autowired
    IWscOrderDao iWscOrderDao;

    @Override
    public List<WscOrder> updateOrdercampus(String cxStr, Long id) {
        return iWscOrderDao.updateOrdercampus(cxStr, id);
    }
}
