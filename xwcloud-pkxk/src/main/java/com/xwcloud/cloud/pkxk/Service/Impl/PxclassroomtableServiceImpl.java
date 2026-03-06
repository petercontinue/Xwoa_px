package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.haveTimeCrVO;
import com.xwcloud.cloud.model.entity.Pxclassroomtable;
import com.xwcloud.cloud.pkxk.Dao.IPxclassroomtableDao;
import com.xwcloud.cloud.pkxk.Service.IPxclassroomtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-24
 */
@Service
public class PxclassroomtableServiceImpl extends ServiceImpl<IPxclassroomtableDao, Pxclassroomtable> implements IPxclassroomtableService {
	@Autowired
    IPxclassroomtableDao iPxclassroomtableDao;

    @Override
    public Page<haveTimeCrVO> gethavetimeclassRoomList(Page page, QueryWrapper queryWrapper) {
        return iPxclassroomtableDao.gethavetimeclassRoomList(page,queryWrapper);
    }
}
