package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.ITuifeishenpiDao;
import com.xwcloud.cloud.caiwu.Service.ITuifeishenpiService;
import com.xwcloud.cloud.model.Vo.shenpiVo;
import com.xwcloud.cloud.model.entity.Tuifeishenpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-08
 */
@Service
public class TuifeishenpiServiceImpl extends ServiceImpl<ITuifeishenpiDao, Tuifeishenpi> implements ITuifeishenpiService {

    @Autowired
    ITuifeishenpiDao iTuifeishenpiDao;

    @Override
    public Page<shenpiVo> getshenpiPage(Page page, QueryWrapper queryWrapper) {
        return iTuifeishenpiDao.getshenpiPage(page, queryWrapper);
    }
}
