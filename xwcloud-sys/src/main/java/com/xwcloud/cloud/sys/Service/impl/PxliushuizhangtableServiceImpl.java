package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.yejidataVO;
import com.xwcloud.cloud.model.entity.Pxliushuizhangtable;
import com.xwcloud.cloud.sys.Dao.IPxliushuizhangtableDao;
import com.xwcloud.cloud.sys.Service.IPxliushuizhangtableService;
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
 * @since 2021-07-01
 */
@Service
public class PxliushuizhangtableServiceImpl extends ServiceImpl<IPxliushuizhangtableDao, Pxliushuizhangtable> implements IPxliushuizhangtableService {

    @Autowired
    IPxliushuizhangtableDao iPxliushuizhangtableDao;

    @Override
    public List<yejidataVO> GetCampusYejiList() {
        return iPxliushuizhangtableDao.GetCampusYejiList();
    }

    @Override
    public BigDecimal GetDayYejiMoney(long qiyeID, long campusID, String date) {
        return iPxliushuizhangtableDao.GetDayYejiMoney(qiyeID, campusID, date);
    }

    @Override
    public BigDecimal GetKehaoMoney(long qiyeID, long campusID, String date) {
        return iPxliushuizhangtableDao.GetKehaoMoney(qiyeID, campusID, date);
    }
}
