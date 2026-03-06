package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.djqSumVo;
import com.xwcloud.cloud.model.entity.Pxdaijinquantable;
import com.xwcloud.cloud.stu.Dao.IPxdaijinquantableDao;
import com.xwcloud.cloud.stu.Service.IPxdaijinquantableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
@Service
public class PxdaijinquantableServiceImpl extends ServiceImpl<IPxdaijinquantableDao, Pxdaijinquantable> implements IPxdaijinquantableService {
    @Autowired
    IPxdaijinquantableDao iPxdaijinquantableDao;

    @Override
    public List<Pxdaijinquantable> getstudjq(Long stuID, Long qiyeID) {
        return iPxdaijinquantableDao.getstudjq(stuID, qiyeID);
    }

    @Override
    public List<djqSumVo> getstudjqSum(Long stuID, Long qiyeID) {
        return iPxdaijinquantableDao.getstudjqSum(stuID, qiyeID);
    }
}
