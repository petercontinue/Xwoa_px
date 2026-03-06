package com.xwcloud.cloud.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaLiushuiYewu;
import com.xwcloud.cloud.model.OA.Vo.YewuLiushuiVo;
import com.xwcloud.cloud.oa.Dao.IOaLiushuiYewuDao;
import com.xwcloud.cloud.oa.service.IOaLiushuiYewuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-13
 */
@Service
public class OaLiushuiYewuServiceImpl extends ServiceImpl<IOaLiushuiYewuDao, OaLiushuiYewu> implements IOaLiushuiYewuService {

    @Autowired
    private IOaLiushuiYewuDao iOaLiushuiYewuDao;

    //获取所有的业务流水信息
    @Override
    public IPage<YewuLiushuiVo> getAllYewuLiushuiInfo(Page<YewuLiushuiVo> page, Wrapper wrapper) {
        return iOaLiushuiYewuDao.getAllYewuLiushuiInfo(page, wrapper);
    }
}
