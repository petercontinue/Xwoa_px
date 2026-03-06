package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;
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
public interface IPxpowertableService extends IService<Pxpowertable> {
    List<Pxpowertable> GetPowersBystaffpostIDandmenuID(Long menuID, Long staffPostID,long qiyeID);
}
