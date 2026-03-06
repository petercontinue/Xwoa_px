package com.xwcloud.cloud.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaSmsBuyrecords;
import com.xwcloud.cloud.model.OA.Vo.SmsBuyrecordsVo;
import com.xwcloud.cloud.oa.Dao.IOaSmsBuyrecordsDao;
import com.xwcloud.cloud.oa.service.IOaSmsBuyrecordsService;
import org.apache.ibatis.annotations.Param;
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
public class OaSmsBuyrecordsServiceImpl extends ServiceImpl<IOaSmsBuyrecordsDao, OaSmsBuyrecords> implements IOaSmsBuyrecordsService {


    @Autowired
    private IOaSmsBuyrecordsDao iOaSmsBuyrecordsDao;

    @Override
    public IPage<SmsBuyrecordsVo> getAllSmsBuyrecordsInfo(Page<SmsBuyrecordsVo> page, @Param("ew") Wrapper wrapper) {
        return iOaSmsBuyrecordsDao.getAllSmsBuyrecordsInfo(page, wrapper);
    }
}
