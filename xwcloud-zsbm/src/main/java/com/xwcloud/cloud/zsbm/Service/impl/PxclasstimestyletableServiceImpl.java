package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxclasstimestyletable;
import com.xwcloud.cloud.zsbm.Dao.IPxclasstimestyletableDao;
import com.xwcloud.cloud.zsbm.Service.IPxclasstimestyletableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-11
 */
@Service
public class PxclasstimestyletableServiceImpl extends ServiceImpl<IPxclasstimestyletableDao, Pxclasstimestyletable> implements IPxclasstimestyletableService {

    @Autowired
    IPxclasstimestyletableDao iPxclasstimestyletableDao;

    @Override
    public List<searchVO> GetAllclasstimestyleList(long qiyeID) {
        return iPxclasstimestyletableDao.GetAllclasstimestyleList(qiyeID);
    }
}
