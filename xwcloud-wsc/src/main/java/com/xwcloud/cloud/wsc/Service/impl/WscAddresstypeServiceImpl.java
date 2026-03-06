package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.entity.WscAddresstype;
import com.xwcloud.cloud.wsc.Dao.IWscAddresstypeDao;
import com.xwcloud.cloud.wsc.Service.IWscAddresstypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-20
 */
@Service
public class WscAddresstypeServiceImpl extends ServiceImpl<IWscAddresstypeDao, WscAddresstype> implements IWscAddresstypeService {
	@Autowired
    IWscAddresstypeDao iWscAddresstypeDao;
}
