package com.xwcloud.cloud.oa.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaStaffpost;
import com.xwcloud.cloud.oa.Dao.IOaStaffpostDao;
import com.xwcloud.cloud.oa.service.IOaStaffpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-29
 */
@Service
public class OaStaffpostServiceImpl extends ServiceImpl<IOaStaffpostDao, OaStaffpost> implements IOaStaffpostService {
    @Autowired
    IOaStaffpostDao iOaStaffpostDao;

    @Override
    public Page<OaStaffpost> getAllStaffpostPages(Page page, QueryWrapper<OaStaffpost> queryWrapper) {
        return iOaStaffpostDao.getAllStaffpostPages(page, queryWrapper);
    }

    @Override
    public OaStaffpost getOneStaffpostByID(long id) {
        return iOaStaffpostDao.getOneStaffpostByID_Dao(id);
    }
}
