package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxqiandaninfotable;
import com.xwcloud.cloud.pkxk.Dao.IPxqiandaninfotableDao;
import com.xwcloud.cloud.pkxk.Service.IPxqiandaninfotableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-30
 */
@Service
public class PxqiandaninfotableServiceImpl extends ServiceImpl<IPxqiandaninfotableDao, Pxqiandaninfotable> implements IPxqiandaninfotableService {
    @Autowired
    IPxqiandaninfotableDao iPxqiandaninfotableDao;

}
