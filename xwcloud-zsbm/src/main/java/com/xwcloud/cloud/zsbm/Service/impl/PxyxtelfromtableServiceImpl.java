package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxyxtelfromtable;
import com.xwcloud.cloud.zsbm.Dao.IPxyxtelfromtableDao;
import com.xwcloud.cloud.zsbm.Service.IPxyxtelfromtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-14
 */
@Service
public class PxyxtelfromtableServiceImpl extends ServiceImpl<IPxyxtelfromtableDao, Pxyxtelfromtable> implements IPxyxtelfromtableService {

    @Autowired
    IPxyxtelfromtableDao iPxyxtelfromtableDao;
    @Override
    public List<Pxyxtelfromtable> GetAllTelFromList(long qiyeID) {
        return iPxyxtelfromtableDao.GetAllTelFromList(qiyeID);
    }


    @Override
    public List<searchVO> getYxSearchtelFrom(Long qiyeID) {
        return iPxyxtelfromtableDao.getYxSearchtelFrom(qiyeID);
    }
}
