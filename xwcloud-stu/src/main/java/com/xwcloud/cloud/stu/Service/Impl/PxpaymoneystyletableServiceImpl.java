package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxpaymoneystyletable;
import com.xwcloud.cloud.stu.Dao.IPxpaymoneystyletableDao;
import com.xwcloud.cloud.stu.Service.IPxpaymoneystyletableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-05
 */
@Service
public class PxpaymoneystyletableServiceImpl extends ServiceImpl<IPxpaymoneystyletableDao, Pxpaymoneystyletable> implements IPxpaymoneystyletableService {
    @Autowired
    IPxpaymoneystyletableDao iPxpaymoneystyletableDao;

    @Override
    public List<Pxpaymoneystyletable> getOnePay(Long qiyeID, String moneystyleName) {
        return iPxpaymoneystyletableDao.getOnePay(qiyeID, moneystyleName);
    }
}
