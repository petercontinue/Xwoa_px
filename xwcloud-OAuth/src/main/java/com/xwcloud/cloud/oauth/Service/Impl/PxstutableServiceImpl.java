package com.xwcloud.cloud.oauth.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.oauth.Dao.IPxstutableDao;
import com.xwcloud.cloud.oauth.Service.IPxstutableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-04
 */
@Service
public class PxstutableServiceImpl extends ServiceImpl<IPxstutableDao, Pxstutable> implements IPxstutableService {

    @Autowired
    IPxstutableDao iPxstutableDao;

    @Override
    public Pxstutable getStuInfoData(QueryWrapper wrapper) {
        return iPxstutableDao.getStuInfoData(wrapper);
    }
}
