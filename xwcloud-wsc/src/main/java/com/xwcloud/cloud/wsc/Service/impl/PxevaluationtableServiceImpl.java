package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxevaluationtable;
import com.xwcloud.cloud.wsc.Dao.IPxevaluationtableDao;
import com.xwcloud.cloud.wsc.Service.IPxevaluationtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-29
 */
@Service
public class PxevaluationtableServiceImpl extends ServiceImpl<IPxevaluationtableDao, Pxevaluationtable> implements IPxevaluationtableService {
    @Autowired
    IPxevaluationtableDao iPxevaluationtableDao;

    @Override
    public Page<HashMap<String, String>> getPxevaluationtableJoinPage(Page page, Wrapper wrapper) {
        return iPxevaluationtableDao.getPxevaluationtableJoinPage(page, wrapper);
    }
}
