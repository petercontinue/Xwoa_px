package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.quanxianfanhui;
import com.xwcloud.cloud.model.entity.Pxpowermenubuttontable;
import com.xwcloud.cloud.model.entity.Pxpowertable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-07
 */
public interface IPxpowermenubuttontableService extends IService<Pxpowermenubuttontable> {
    List<Pxpowermenubuttontable> GetpowermenuButtonListBymenuID(Long menuID);
    List<Pxpowermenubuttontable> GetpowermenuButtonListBymenuIDAndbuttonID(Long menuID,Long buttonID);
    List<quanxianfanhui> getmenusbuttonList(long menuID);
    Pxpowertable GetStaffpostmenuPower(long staffpostID, long menuID, long qiyeID);
}
