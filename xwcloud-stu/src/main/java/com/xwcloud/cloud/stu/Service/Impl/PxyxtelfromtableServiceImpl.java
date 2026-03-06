package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxyxtelfromtable;
import com.xwcloud.cloud.stu.Dao.IPxyxtelfromtableDao;
import com.xwcloud.cloud.stu.Service.IPxyxtelfromtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-05
 */
@Service
public class PxyxtelfromtableServiceImpl extends ServiceImpl<IPxyxtelfromtableDao, Pxyxtelfromtable> implements IPxyxtelfromtableService {
    @Autowired
    IPxyxtelfromtableDao iPxyxtelfromtableDao;

    @Override
    public List<Pxyxtelfromtable> getOneTelfrom(Long qiyeID) {
        return iPxyxtelfromtableDao.getOneTelfrom(qiyeID);
    }
}
