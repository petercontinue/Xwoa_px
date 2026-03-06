package com.xwcloud.cloud.sys.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.WscTuikelevel;
import com.xwcloud.cloud.sys.Dao.IWscTuikelevelDao;
import com.xwcloud.cloud.sys.Service.IWscTuikelevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-19
 */
@Service
public class WscTuikelevelServiceImpl extends ServiceImpl<IWscTuikelevelDao, WscTuikelevel> implements IWscTuikelevelService {
    @Autowired
    IWscTuikelevelDao iWscTuikelevelDao;


    @Override
    public Page<WscTuikelevel> getpage(Page page, QueryWrapper queryWrapper) {
        return iWscTuikelevelDao.getpage(page, queryWrapper);
    }
}
