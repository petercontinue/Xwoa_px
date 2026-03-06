package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxqiandaninfotable;
import com.xwcloud.cloud.stu.Dao.IPxqiandaninfotableDao;
import com.xwcloud.cloud.stu.Service.IPxqiandaninfotableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
public class PxqiandaninfotableServiceImpl extends ServiceImpl<IPxqiandaninfotableDao, Pxqiandaninfotable> implements IPxqiandaninfotableService {
    @Autowired
    IPxqiandaninfotableDao iPxqiandaninfotableDao;

    @Override
    public List<Pxqiandaninfotable> getQD(Long stuID, Long qiyeID) {
        return iPxqiandaninfotableDao.getQD(stuID, qiyeID);
    }

    @Override
    public List<Pxqiandaninfotable> getdelzsQD(Long stuID, BigDecimal sMoney, Long qiyeID) {
        return iPxqiandaninfotableDao.getdelzsQD(stuID, sMoney, qiyeID);
    }

}
