package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.qiandanpaymoneyVO;
import com.xwcloud.cloud.model.entity.Pxqiandanpaymoney;
import com.xwcloud.cloud.zsbm.Dao.IPxqiandanpaymoneyDao;
import com.xwcloud.cloud.zsbm.Service.IPxqiandanpaymoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-11
 */
@Service
public class PxqiandanpaymoneyServiceImpl extends ServiceImpl<IPxqiandanpaymoneyDao, Pxqiandanpaymoney> implements IPxqiandanpaymoneyService {
    @Autowired
    IPxqiandanpaymoneyDao iPxqiandanpaymoneyDao;

    @Override
    public Pxqiandanpaymoney GetQiandanPayMoneyStyleByqdID(Long qiandanID, Long paymoneyStyleID) {
        return iPxqiandanpaymoneyDao.GetQiandanPayMoneyStyleByqdID(qiandanID, paymoneyStyleID);
    }

    @Override
    public List<Pxqiandanpaymoney> getQiandanPayMoneyList(Long qiandanID) {
        return iPxqiandanpaymoneyDao.getQiandanPayMoneyList(qiandanID);
    }

    @Override
    public int deleteQiandanPayMoneybyqiandanID(Long qiandanID) {
        return iPxqiandanpaymoneyDao.deleteQiandanPayMoneybyqiandanID(qiandanID);
    }

    @Override
    public List<qiandanpaymoneyVO> getqiandanPayMoneyList(Long qiandanID) {
        return iPxqiandanpaymoneyDao.getqiandanPayMoneyList(qiandanID);
    }

    @Override
    public List<HashMap<String, Object>> getPaystyletoPayweikuan(QueryWrapper queryWrapper) {
        return iPxqiandanpaymoneyDao.getPaystyletoPayweikuan(queryWrapper);
    }
}
