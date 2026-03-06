package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.assetAddVO;
import com.xwcloud.cloud.model.entity.Pxassetsouttable;
import com.xwcloud.cloud.sys.Dao.IPxassetsouttableDao;
import com.xwcloud.cloud.sys.Service.IPxassetsouttableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-22
 */
@Service
public class PxassetsouttableServiceImpl extends ServiceImpl<IPxassetsouttableDao, Pxassetsouttable> implements IPxassetsouttableService {

    @Autowired
    IPxassetsouttableDao iPxassetsouttableDao;

    @Override
    public Page<assetAddVO> GetassetsOutPages(Page page, QueryWrapper wrapper) {
        return iPxassetsouttableDao.GetassetsOutPages(page, wrapper);
    }
}
