package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.caiwu.Dao.IPxpaymoneystyletableDao;
import com.xwcloud.cloud.caiwu.Service.IPxpaymoneystyletableService;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxpaymoneystyletable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-08
 */
@Service
public class PxpaymoneystyletableServiceImpl extends ServiceImpl<IPxpaymoneystyletableDao, Pxpaymoneystyletable> implements IPxpaymoneystyletableService {
    @Autowired
    IPxpaymoneystyletableDao iPxpaymoneystyletableDao;

    @Override
    public List<listVo> getpaystyle(Long qiyeID) {
        return iPxpaymoneystyletableDao.getpaystyle(qiyeID);
    }
}
