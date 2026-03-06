package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.zhaoshengStaffMubaoVo;
import com.xwcloud.cloud.model.Vo.zhaoshengmubiaoVo;
import com.xwcloud.cloud.model.entity.Pxzhaoshenmubiaocampustable;
import com.xwcloud.cloud.zsbm.Dao.IPxzhaoshenmubiaocampustableDao;
import com.xwcloud.cloud.zsbm.Service.IPxzhaoshenmubiaocampustableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-26
 */
@Service
//@DS("#header.DBname")
public class PxzhaoshenmubiaocampustableServiceImpl extends ServiceImpl<IPxzhaoshenmubiaocampustableDao, Pxzhaoshenmubiaocampustable> implements IPxzhaoshenmubiaocampustableService {

    @Autowired
    IPxzhaoshenmubiaocampustableDao iPxzhaoshenmubiaocampustableDao;

    @Override
    public Page<zhaoshengmubiaoVo> GetZhaoshengmubiaoCampusPages(Page page, QueryWrapper wrapper) {
        return iPxzhaoshenmubiaocampustableDao.GetZhaoshengmubiaoCampusPages(page, wrapper);
    }

    @Override
    public Page<zhaoshengStaffMubaoVo> getStaffZhaoshengmubiaoPages(Page<zhaoshengStaffMubaoVo> page, QueryWrapper<zhaoshengStaffMubaoVo> wrapper) {
        return iPxzhaoshenmubiaocampustableDao.getStaffZhaoshengmubiaoPages(page, wrapper);
    }

    @Override
    public List<Pxzhaoshenmubiaocampustable> GetListcampusMubiao(Long campusID, String yearMonth) {
        return iPxzhaoshenmubiaocampustableDao.GetListcampusMubiao(campusID, yearMonth);
    }
}
