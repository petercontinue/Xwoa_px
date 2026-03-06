package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxjiekevalue;

import com.xwcloud.cloud.stu.Dao.IPxjiekevalueDao;
import com.xwcloud.cloud.stu.Service.IPxjiekevalueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-27
 */
@Service
public class PxjiekevalueServiceImpl extends ServiceImpl<IPxjiekevalueDao, Pxjiekevalue> implements IPxjiekevalueService {
    @Autowired
    IPxjiekevalueDao iPxjiekevalueDao;
}
