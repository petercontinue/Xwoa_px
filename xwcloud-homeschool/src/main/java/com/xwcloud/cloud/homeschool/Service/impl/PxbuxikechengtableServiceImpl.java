package com.xwcloud.cloud.homeschool.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxbuxikechengtableDao;
import com.xwcloud.cloud.homeschool.Service.IPxbuxikechengtableService;
import com.xwcloud.cloud.model.Vo.SumbxRemainVo;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-16
 */
@Service
public class PxbuxikechengtableServiceImpl extends ServiceImpl<IPxbuxikechengtableDao, Pxbuxikechengtable> implements IPxbuxikechengtableService {
    @Autowired
    IPxbuxikechengtableDao iPxbuxikechengtableDao;

    @Override
    public List<SumbxRemainVo> getSumzongRks(Long stuID) {
        return iPxbuxikechengtableDao.getSumzongRks(stuID);
    }

    @Override
    public List<SumbxRemainVo> getSumRks(Long stuID, Long kechengID, Long qiyeID) {
        return iPxbuxikechengtableDao.getSumRks(stuID, kechengID, qiyeID);
    }
}
