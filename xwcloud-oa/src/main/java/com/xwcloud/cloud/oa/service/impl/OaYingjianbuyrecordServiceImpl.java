package com.xwcloud.cloud.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaYingjianbuyrecord;
import com.xwcloud.cloud.model.OA.Vo.YingjianInfoVo;
import com.xwcloud.cloud.oa.Dao.IOaYingjianbuyrecordDao;
import com.xwcloud.cloud.oa.service.IOaYingjianbuyrecordService;
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
public class OaYingjianbuyrecordServiceImpl extends ServiceImpl<IOaYingjianbuyrecordDao, OaYingjianbuyrecord> implements IOaYingjianbuyrecordService {

    @Autowired
    private IOaYingjianbuyrecordDao iOaYingjianbuyrecordDao;

    @Override
    public IPage<YingjianInfoVo> getAllYingjianrecordInfo(Page<YingjianInfoVo> page, Wrapper wrapper) {
        return iOaYingjianbuyrecordDao.getAllYingjianrecordInfo(page, wrapper);
    }

    @Override
    public YingjianInfoVo getOneYingjianrecordById(Long id) {
        return iOaYingjianbuyrecordDao.getOneYingjianrecordById(id);
    }
}
