package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxqiandanpaymoney;

import com.xwcloud.cloud.stu.Dao.IPxqiandanpaymoneyDao;
import com.xwcloud.cloud.stu.Service.IPxqiandanpaymoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-05
 */
@Service
public class PxqiandanpaymoneyServiceImpl extends ServiceImpl<IPxqiandanpaymoneyDao, Pxqiandanpaymoney> implements IPxqiandanpaymoneyService {
    @Autowired
    IPxqiandanpaymoneyDao iPxqiandanpaymoneyDao;
}
