package com.xwcloud.cloud.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaWorkrizhi;
import com.xwcloud.cloud.model.OA.Vo.WorkrizhiVo;
import com.xwcloud.cloud.oa.Dao.IOaWorkrizhiDao;
import com.xwcloud.cloud.oa.service.IOaWorkrizhiService;
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
public class OaWorkrizhiServiceImpl extends ServiceImpl<IOaWorkrizhiDao, OaWorkrizhi> implements IOaWorkrizhiService {

    @Autowired
    private IOaWorkrizhiDao iOaWorkrizhiDao;

    //获取所有的workrizhi信息
    @Override
    public IPage<WorkrizhiVo> getAllWorkrizhiInfo(Page<WorkrizhiVo> page, Wrapper wrapper) {
        return iOaWorkrizhiDao.getAllWorkrizhiInfo(page, wrapper);
    }

    @Override
    public WorkrizhiVo getOneWorkrizhiById(Long id) {
        return iOaWorkrizhiDao.getOneWorkrizhiById(id);
    }
}
