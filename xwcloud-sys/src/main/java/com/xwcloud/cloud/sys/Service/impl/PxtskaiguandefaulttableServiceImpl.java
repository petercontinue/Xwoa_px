package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxtskaiguandefaulttable;
import com.xwcloud.cloud.sys.Dao.IPxtskaiguandefaulttableDao;
import com.xwcloud.cloud.sys.Service.IPxtskaiguandefaulttableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-07
 */
@Service
//@DS("#header.DBname")
public class PxtskaiguandefaulttableServiceImpl extends ServiceImpl<IPxtskaiguandefaulttableDao, Pxtskaiguandefaulttable> implements IPxtskaiguandefaulttableService {

    @Autowired
    IPxtskaiguandefaulttableDao iPxtskaiguandefaulttableDao;

    @Override
    public int UpdateTsKaiGuan(String value, String id) {
        return iPxtskaiguandefaulttableDao.UpdateTsKaiGuan(value, id);
    }
}
