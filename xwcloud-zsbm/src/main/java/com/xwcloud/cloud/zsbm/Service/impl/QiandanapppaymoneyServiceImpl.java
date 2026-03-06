package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Qiandanapppaymoney;
import com.xwcloud.cloud.zsbm.Dao.IQiandanapppaymoneyDao;
import com.xwcloud.cloud.zsbm.Service.IQiandanapppaymoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-28
 */
@Service
public class QiandanapppaymoneyServiceImpl extends ServiceImpl<IQiandanapppaymoneyDao, Qiandanapppaymoney> implements IQiandanapppaymoneyService {
    @Autowired
    IQiandanapppaymoneyDao iQiandanapppaymoneyDao;
}
