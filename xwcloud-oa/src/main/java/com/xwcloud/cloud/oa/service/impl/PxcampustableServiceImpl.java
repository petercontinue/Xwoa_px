package com.xwcloud.cloud.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.Vo.PxcampusVo;
import com.xwcloud.cloud.model.entity.Pxcampustable;
import com.xwcloud.cloud.oa.Dao.IPxcampustableDao;
import com.xwcloud.cloud.oa.service.IPxcampustableService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-05
 */
@Service
public class PxcampustableServiceImpl extends ServiceImpl<IPxcampustableDao, Pxcampustable> implements IPxcampustableService {

    @Autowired
    private IPxcampustableDao iPxcampustableDao;

    //分页获取所有的校区信息
    @Override
    public IPage<PxcampusVo> getAllPxcampusInfo(Page<PxcampusVo> page, @Param("ew") Wrapper wrapper) {
        return iPxcampustableDao.getAllPxcampusInfo(page, wrapper);
    }

    //根据id获取一条校区信息
    @Override
    public PxcampusVo getOnePxcampusById(Long id) {
        return iPxcampustableDao.getOnePxcampusById(id);
    }
}
