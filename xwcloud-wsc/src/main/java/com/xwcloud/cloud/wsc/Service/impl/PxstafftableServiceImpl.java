package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstafftable;
import com.xwcloud.cloud.wsc.Dao.IPxstafftableDao;
import com.xwcloud.cloud.wsc.Service.IPxstafftableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-11
 */
@Service
public class PxstafftableServiceImpl extends ServiceImpl<IPxstafftableDao, Pxstafftable> implements IPxstafftableService {
    @Autowired
    IPxstafftableDao iPxstafftableDao;

    @Override
    public List<HashMap<String, Object>> GetStaffInfo(QueryWrapper wrapper) {
        return iPxstafftableDao.GetStaffInfo(wrapper);
    }

    @Override
    public List<HashMap<String, Object>> GetgerenStaffInfo(QueryWrapper queryWrapper) {
        return iPxstafftableDao.GetgerenStaffInfo(queryWrapper);
    }

    @Override
    public List<HashMap<String, Object>> GetgerenDongtaiInfo(QueryWrapper wrapper) {
        return iPxstafftableDao.GetgerenDongtaiInfo(wrapper);
    }

    @Override
    public List<HashMap<String, Object>> GetgerenStaffInfotoIndexpage(QueryWrapper wrapper) {
        return iPxstafftableDao.GetgerenStaffInfotoIndexpage(wrapper);
    }

    @Override
    public List<HashMap<String, Object>> getappteaInfo(QueryWrapper wrapper) {
        return iPxstafftableDao.getappteaInfo(wrapper);
    }
}
