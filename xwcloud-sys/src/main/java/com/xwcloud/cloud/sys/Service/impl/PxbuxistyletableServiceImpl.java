package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxbuxistyletable;
import com.xwcloud.cloud.model.entity.Pxkechengtable;
import com.xwcloud.cloud.sys.Dao.IPxbuxistyletableDao;
import com.xwcloud.cloud.sys.Service.IPxbuxistyletableService;
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
public class PxbuxistyletableServiceImpl extends ServiceImpl<IPxbuxistyletableDao, Pxbuxistyletable> implements IPxbuxistyletableService {

    @Autowired
    IPxbuxistyletableDao iPxbuxistyletableDao;
    @Override
    public List<Pxkechengtable> GetKechengByBuxistyleID(String buxiStyleID) {
        return iPxbuxistyletableDao.GetKechengByBuxistyleID(buxiStyleID);
    }
}
