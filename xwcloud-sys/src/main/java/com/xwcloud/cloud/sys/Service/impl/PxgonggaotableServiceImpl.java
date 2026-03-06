package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.gonggaoVO;
import com.xwcloud.cloud.model.entity.Pxgonggaotable;
import com.xwcloud.cloud.sys.Dao.IPxgonggaotableDao;
import com.xwcloud.cloud.sys.Service.IPxgonggaotableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-22
 */
@Service
public class PxgonggaotableServiceImpl extends ServiceImpl<IPxgonggaotableDao, Pxgonggaotable> implements IPxgonggaotableService {

    @Autowired
    IPxgonggaotableDao iPxgonggaotableDao;

    @Override
    public Page<gonggaoVO> GetgonggaoPages(Page page, QueryWrapper wrapper) {
        return iPxgonggaotableDao.GetgonggaoPages(page, wrapper);
    }

    @Override
    public List<gonggaoVO> GetgonggaoList(QueryWrapper wrapper) {
        return iPxgonggaotableDao.GetgonggaoList(wrapper);
    }
}
