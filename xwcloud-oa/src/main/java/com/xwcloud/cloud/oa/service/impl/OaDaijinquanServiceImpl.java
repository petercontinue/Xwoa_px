package com.xwcloud.cloud.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaDaijinquan;
import com.xwcloud.cloud.model.OA.Vo.DaijinquanVo;
import com.xwcloud.cloud.oa.Dao.IOaDaijinquanDao;
import com.xwcloud.cloud.oa.service.IOaDaijinquanService;
import org.apache.ibatis.annotations.Param;
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
public class OaDaijinquanServiceImpl extends ServiceImpl<IOaDaijinquanDao, OaDaijinquan> implements IOaDaijinquanService {

    @Autowired
    private IOaDaijinquanDao iOaDaijinquanDao;

    //获取所有的代金券信息
    @Override
    public IPage<DaijinquanVo> getAllDaijinquanInfo(Page<DaijinquanVo> page, @Param("ew") Wrapper wrapper) {
        return iOaDaijinquanDao.getAllDaijinquanInfo(page, wrapper);
    }

    //根据id获取一个代金券信息
    @Override
    public DaijinquanVo getOneDaijinquanById(Long id) {
        return iOaDaijinquanDao.getOneDaijinquanById(id);
    }
}
