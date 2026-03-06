package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.model.entity.Pxyxtelfromtable;
import com.xwcloud.cloud.sys.Dao.IPxyxtelfromtableDao;
import com.xwcloud.cloud.sys.Service.IPxyxtelfromtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-24
 */
@Service
//@DS("#header.DBname")
public class PxyxtelfromtableServiceImpl extends ServiceImpl<IPxyxtelfromtableDao, Pxyxtelfromtable> implements IPxyxtelfromtableService {
    @Autowired
    IPxyxtelfromtableDao iPxyxtelfromtableDao;

    @Override
    public List<Pxstutable> GetstuBytelFromID(String yxFromID) {
        return iPxyxtelfromtableDao.GetstuBytelFromID(yxFromID);
    }
}
