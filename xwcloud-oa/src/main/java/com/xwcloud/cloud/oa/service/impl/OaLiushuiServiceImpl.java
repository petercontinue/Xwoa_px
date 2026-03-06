package com.xwcloud.cloud.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaLiushui;
import com.xwcloud.cloud.model.OA.Vo.LiushuiInfoVo;
import com.xwcloud.cloud.oa.Dao.IOaLiushuiDao;
import com.xwcloud.cloud.oa.service.IOaLiushuiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-29
 */
@Service
public class OaLiushuiServiceImpl extends ServiceImpl<IOaLiushuiDao, OaLiushui> implements IOaLiushuiService {

    @Autowired
    private IOaLiushuiDao iOaLiushuiDao;

    @Override
    public IPage<LiushuiInfoVo> getAllLiushuiInfo(Page<LiushuiInfoVo> page, Wrapper wrapper) {
        return iOaLiushuiDao.getAllLiushuiInfo(page, wrapper);
    }

    @Override
    public LiushuiInfoVo getOneLiushuiInfoById(Long id) {
        return iOaLiushuiDao.getOneLiushuiInfoById(id);
    }
}
