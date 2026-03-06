package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxpaymoneystyletable;
import com.xwcloud.cloud.sys.Dao.IPxpaymoneystyletableDao;
import com.xwcloud.cloud.sys.Service.IPxpaymoneystyletableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-22
 */
@Service
//@DS("#header.DBname")
public class PxpaymoneystyletableServiceImpl extends ServiceImpl<IPxpaymoneystyletableDao, Pxpaymoneystyletable> implements IPxpaymoneystyletableService {

    @Autowired
    IPxpaymoneystyletableDao iPxpaymoneystyletableDao;
    @Override
    public List<Pxpaymoneystyletable> GetAllPaymoneystyleList(Long qiyeID) {
        return iPxpaymoneystyletableDao.GetAllPaymoneystyleList(qiyeID);
    }
}
