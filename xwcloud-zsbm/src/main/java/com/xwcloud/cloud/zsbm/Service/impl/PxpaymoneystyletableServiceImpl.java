package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxpaymoneystyletable;
import com.xwcloud.cloud.zsbm.Dao.IPxpaymoneystyletableDao;
import com.xwcloud.cloud.zsbm.Service.IPxpaymoneystyletableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-18
 */
@Service
public class PxpaymoneystyletableServiceImpl extends ServiceImpl<IPxpaymoneystyletableDao, Pxpaymoneystyletable> implements IPxpaymoneystyletableService {
    @Autowired
    IPxpaymoneystyletableDao iPxpaymoneystyletableDao;
}
