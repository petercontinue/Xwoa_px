package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.IPxstutableDao;
import com.xwcloud.cloud.caiwu.Service.IPxstutableService;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxstutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Service
public class PxstutableServiceImpl extends ServiceImpl<IPxstutableDao, Pxstutable> implements IPxstutableService {
    @Autowired
    IPxstutableDao iPxstutableDao;

    @Override
    public List<listVo> getallstu(QueryWrapper queryWrapper) {
        return iPxstutableDao.getallstu(queryWrapper);
    }
}
