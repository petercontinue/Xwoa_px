package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.cxCampusVO;
import com.xwcloud.cloud.model.entity.Pxcampustable;
import com.xwcloud.cloud.sys.Dao.IPxcampustableDao;
import com.xwcloud.cloud.sys.Service.IPxcampustableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-24
 */
@Service
//@DS("#header.DBname")
public class PxcampustableServiceImpl extends ServiceImpl<IPxcampustableDao, Pxcampustable> implements IPxcampustableService {

    @Autowired
    IPxcampustableDao iPxcampustableDao;
    @Override
    public List<Pxcampustable> getAllList() {
        return iPxcampustableDao.getAllList();
    }

    @Override
    public List<cxCampusVO> GetSearchCampusList(QueryWrapper queryWrapper) {
        return iPxcampustableDao.GetSearchCampusList(queryWrapper);
    }

    @Override
    public List<HashMap<String, Object>> getjigou(QueryWrapper queryWrapper) {
        return iPxcampustableDao.getjigou(queryWrapper);
    }
}
