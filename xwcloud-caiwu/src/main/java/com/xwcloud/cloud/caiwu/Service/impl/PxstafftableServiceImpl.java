package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.caiwu.Dao.IPxstafftableDao;
import com.xwcloud.cloud.caiwu.Service.IPxstafftableService;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxstafftable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-08
 */
@Service
public class PxstafftableServiceImpl extends ServiceImpl<IPxstafftableDao, Pxstafftable> implements IPxstafftableService {
    @Autowired
    IPxstafftableDao iPxstafftableDao;


    @Override
    public List<listVo> getallStaff(QueryWrapper queryWrapper) {
        return iPxstafftableDao.getallStaff(queryWrapper);
    }
}
