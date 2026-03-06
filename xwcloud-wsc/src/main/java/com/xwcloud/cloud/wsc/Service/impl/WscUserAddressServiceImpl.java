package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.WscUserAddress;
import com.xwcloud.cloud.wsc.Dao.IWscUserAddressDao;
import com.xwcloud.cloud.wsc.Service.IWscUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-19
 */
@Service
public class WscUserAddressServiceImpl extends ServiceImpl<IWscUserAddressDao, WscUserAddress> implements IWscUserAddressService {
    @Autowired
    IWscUserAddressDao iWscUserAddressDao;

    @Override
    public Page<HashMap<String, Object>> getpage(Page page, QueryWrapper queryWrapper) {
        return iWscUserAddressDao.getpage(page, queryWrapper);
    }
}
