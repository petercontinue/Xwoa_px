package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.wechatMessageVO;
import com.xwcloud.cloud.model.entity.Pxtuisongtable;
import com.xwcloud.cloud.sys.Dao.IPxtuisongtableDao;
import com.xwcloud.cloud.sys.Service.IPxtuisongtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-15
 */
@Service
//@DS("#header.DBname")
public class PxtuisongtableServiceImpl extends ServiceImpl<IPxtuisongtableDao, Pxtuisongtable> implements IPxtuisongtableService {
    @Autowired
    IPxtuisongtableDao iPxtuisongtableDao;

    @Override
    public Page<wechatMessageVO> GetyuangongWechatMessagesPages(Page page, QueryWrapper wrapper) {
        return iPxtuisongtableDao.GetyuangongWechatMessagesPages(page, wrapper);
    }
}
