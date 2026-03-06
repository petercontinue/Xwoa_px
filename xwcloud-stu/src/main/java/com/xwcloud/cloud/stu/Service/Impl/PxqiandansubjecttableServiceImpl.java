package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxqiandansubjecttable;
import com.xwcloud.cloud.stu.Dao.IPxqiandansubjecttableDao;
import com.xwcloud.cloud.stu.Service.IPxqiandansubjecttableService;
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
public class PxqiandansubjecttableServiceImpl extends ServiceImpl<IPxqiandansubjecttableDao, Pxqiandansubjecttable> implements IPxqiandansubjecttableService {
    @Autowired
    IPxqiandansubjecttableDao iPxqiandansubjecttableDao;

    @Override
    public List<Pxqiandansubjecttable> getqdSubject(Long stuID, Long qiyeID) {
        return iPxqiandansubjecttableDao.getqdSubject(stuID, qiyeID);
    }

    @Override
    public List<Pxqiandansubjecttable> getdelzsQdSub(int kcSty, Long stuID, Long kechengID, BigDecimal buykeshiNum, Long qiyeID) {
        return iPxqiandansubjecttableDao.getdelzsQdSub(kcSty, stuID, kechengID, buykeshiNum, qiyeID);
    }
}
