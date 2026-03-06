package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.SumbxRemainVo;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;
import com.xwcloud.cloud.sys.Dao.IPxbuxikechengtableDao;
import com.xwcloud.cloud.sys.Service.IPxbuxikechengtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-29
 */
@Service
public class PxbuxikechengtableServiceImpl extends ServiceImpl<IPxbuxikechengtableDao, Pxbuxikechengtable> implements IPxbuxikechengtableService {
    @Autowired
    IPxbuxikechengtableDao iPxbuxikechengtableDao;

    @Override
    public List<Pxbuxikechengtable> getNOClasskc(QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getNOClasskc(queryWrapper);
    }

    @Override
    public List<SumbxRemainVo> getSumzongRks(Long stuID,Long qiyeID) {
        return iPxbuxikechengtableDao.getSumzongRks(stuID,qiyeID);
    }

    @Override
    public List<SumbxRemainVo> getSumRks(Long stuID, Long kechengID, Long qiyeID) {
        return iPxbuxikechengtableDao.getSumRks(stuID, kechengID, qiyeID);
    }
}
