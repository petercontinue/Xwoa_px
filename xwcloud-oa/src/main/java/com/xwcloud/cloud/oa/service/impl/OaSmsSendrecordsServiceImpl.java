package com.xwcloud.cloud.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaSmsSendrecords;
import com.xwcloud.cloud.model.OA.Vo.SmsSendrecordsVo;
import com.xwcloud.cloud.oa.Dao.IOaSmsSendrecordsDao;
import com.xwcloud.cloud.oa.service.IOaSmsSendrecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-03
 */
@Service
public class OaSmsSendrecordsServiceImpl extends ServiceImpl<IOaSmsSendrecordsDao, OaSmsSendrecords> implements IOaSmsSendrecordsService {

    @Autowired
    private IOaSmsSendrecordsDao iOaSmsSendrecordsDao;

    @Override
    public IPage<SmsSendrecordsVo> getAllSmsBuyrecordsVoInfo(Page<SmsSendrecordsVo> page, Wrapper wrapper) {
        return iOaSmsSendrecordsDao.getAllSmsSendecordsVoInfo(page, wrapper);
    }
}
