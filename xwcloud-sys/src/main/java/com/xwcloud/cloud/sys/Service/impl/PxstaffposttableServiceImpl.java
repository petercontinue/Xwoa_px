package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.cxStaffpostVO;
import com.xwcloud.cloud.model.Vo.staffpostVo;
import com.xwcloud.cloud.model.entity.Pxstaffposttable;
import com.xwcloud.cloud.sys.Dao.IPxstaffposttableDao;
import com.xwcloud.cloud.sys.Service.IPxstaffposttableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yinqi
 * @since 2020-10-20
 */
@Service
//@DS("#header.DBname")
public class PxstaffposttableServiceImpl extends ServiceImpl<IPxstaffposttableDao, Pxstaffposttable> implements IPxstaffposttableService {

    @Autowired
    IPxstaffposttableDao iPxstaffposttableDao;
    @Override
    public Page<staffpostVo> getStaffpostList(Page page, QueryWrapper wrapper) {
        return iPxstaffposttableDao.getStaffpostList(page,wrapper);
    }

    @Override
    public List<Pxstaffposttable> getAllList() {
        return iPxstaffposttableDao.getAllList();
    }

    @Override
    public List<staffpostVo> GetStaffPostListByCampusID(long campusID) {
        return iPxstaffposttableDao.GetStaffPostListByCampusID(campusID);
    }

    @Override
    public List<cxStaffpostVO> GetSearchStaffPostList(long qiyeID) {
        return iPxstaffposttableDao.GetSearchStaffPostList(qiyeID);
    }
}
