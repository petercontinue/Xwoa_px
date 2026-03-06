package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxsysparamdefaulttable;
import com.xwcloud.cloud.sys.Dao.IPxsysparamdefaulttableDao;
import com.xwcloud.cloud.sys.Service.IPxsysparamdefaulttableService;
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
public class PxsysparamdefaulttableServiceImpl extends ServiceImpl<IPxsysparamdefaulttableDao, Pxsysparamdefaulttable> implements IPxsysparamdefaulttableService {

    @Autowired
    IPxsysparamdefaulttableDao iPxsysparamdefaulttableDao;

    @Override
    public int UpdateSysParamValue(String id, String value) {
        return iPxsysparamdefaulttableDao.UpdateSysParamValue(id, value);
    }
}
