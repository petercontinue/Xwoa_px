package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxpowermenubuttontable;
import com.xwcloud.cloud.model.entity.Pxpowertable;

import com.xwcloud.cloud.model.Vo.quanxianfanhui;
import com.xwcloud.cloud.sys.Dao.IPxpowermenubuttontableDao;
import com.xwcloud.cloud.sys.Service.IPxpowermenubuttontableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-07
 */
@Service
//@DS("#header.DBname")
public class PxpowermenubuttontableServiceImpl extends ServiceImpl<IPxpowermenubuttontableDao, Pxpowermenubuttontable> implements IPxpowermenubuttontableService {

    @Autowired
    IPxpowermenubuttontableDao iPxpowermenubuttontableDao;

    @Override
    public List<Pxpowermenubuttontable> GetpowermenuButtonListBymenuID(Long menuID) {
        return iPxpowermenubuttontableDao.GetpowermenuButtonListBymenuID(menuID);
    }

    @Override
    public List<Pxpowermenubuttontable> GetpowermenuButtonListBymenuIDAndbuttonID(Long menuID, Long buttonID) {
        return iPxpowermenubuttontableDao.GetpowermenuButtonListBymenuIDAndbuttonID(menuID, buttonID);
    }

    @Override
    public List<quanxianfanhui> getmenusbuttonList(long menuID) {
        return iPxpowermenubuttontableDao.getmenusbuttonList(menuID);
    }

    @Override
    public Pxpowertable GetStaffpostmenuPower(long staffpostID, long menuID, long qiyeID) {
        return iPxpowermenubuttontableDao.GetStaffpostmenuPower(staffpostID,menuID,qiyeID);
    }
}
