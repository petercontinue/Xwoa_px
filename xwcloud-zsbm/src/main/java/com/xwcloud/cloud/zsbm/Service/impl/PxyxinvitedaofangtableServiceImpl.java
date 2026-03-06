package com.xwcloud.cloud.zsbm.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.daofangVo;
import com.xwcloud.cloud.model.entity.Pxyxinvitedaofangtable;
import com.xwcloud.cloud.zsbm.Dao.IPxyxinvitedaofangtableDao;
import com.xwcloud.cloud.zsbm.Service.IPxyxinvitedaofangtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Service
public class PxyxinvitedaofangtableServiceImpl extends ServiceImpl<IPxyxinvitedaofangtableDao, Pxyxinvitedaofangtable> implements IPxyxinvitedaofangtableService {

    @Autowired
    IPxyxinvitedaofangtableDao ipxyxinvitedaofangtableDao;

    @Override
    public Page<daofangVo> GetinvitationDaofangByStuIDPages(Page<daofangVo> page, QueryWrapper<daofangVo> wrapper) {
        return ipxyxinvitedaofangtableDao.GetinvitationDaofangByStuIDPages(page, wrapper);
    }
	
}
