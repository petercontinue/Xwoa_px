package com.xwcloud.cloud.oa.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaHuifang;
import com.xwcloud.cloud.model.OA.Vo.HuifangVo;
import com.xwcloud.cloud.oa.Dao.IOaHuifangDao;
import com.xwcloud.cloud.oa.service.IOaHuifangService;
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
public class OaHuifangServiceImpl extends ServiceImpl<IOaHuifangDao, OaHuifang> implements IOaHuifangService {

    @Autowired
    private IOaHuifangDao iOaHuifangDao;

    //分页获取所有回访记录
    @Override
    public IPage<HuifangVo> getAllHuifangInfo(Page<HuifangVo> page) {
        return iOaHuifangDao.getAllHuifangInfo(page);
    }

    //根据id获取一条回访记录
    @Override
    public HuifangVo getOneHuifangById(Long id) {
        return iOaHuifangDao.getOneHuifangById(id);
    }

}
