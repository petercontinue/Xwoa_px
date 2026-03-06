package com.xwcloud.cloud.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaHehuoren;
import com.xwcloud.cloud.model.OA.Vo.HehuorenVo;
import com.xwcloud.cloud.oa.Dao.IOaHehuorenDao;
import com.xwcloud.cloud.oa.service.IOaHehuorenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-02
 */
@Service
public class OaHehuorenServiceImpl extends ServiceImpl<IOaHehuorenDao, OaHehuoren> implements IOaHehuorenService {

    @Autowired
    private IOaHehuorenDao iOaHehuorenDao;

    //分页获取所有的合伙人信息
    @Override
    public IPage<HehuorenVo> getAllHehuorenInfo(Page<HehuorenVo> page, Wrapper wrapper) {
        return iOaHehuorenDao.getAllHehuorenInfo(page, wrapper);
    }

    //获取一个合伙人信息
    @Override
    public HehuorenVo getOneHehuorenById(Long id) {
        return iOaHehuorenDao.getOneHehuorenById(id);
    }
}
