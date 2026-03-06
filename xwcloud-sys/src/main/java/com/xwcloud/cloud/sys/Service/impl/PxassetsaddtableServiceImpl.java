package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.assetAddVO;
import com.xwcloud.cloud.model.entity.Pxassetsaddtable;
import com.xwcloud.cloud.sys.Dao.IPxassetsaddtableDao;
import com.xwcloud.cloud.sys.Service.IPxassetsaddtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-25
 */
@Service
public class PxassetsaddtableServiceImpl extends ServiceImpl<IPxassetsaddtableDao, Pxassetsaddtable> implements IPxassetsaddtableService {
    @Autowired
    IPxassetsaddtableDao iPxassetsaddtableDao;

    @Override
    public Page<assetAddVO> GetassetsAddPages(Page page, Wrapper wrapper) {
        return iPxassetsaddtableDao.GetassetsAddPages(page, wrapper);
    }
}
