package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxsysparamvaluetable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-30
 */
public interface IPxsysparamvaluetableService extends IService<Pxsysparamvaluetable> {
    Pxsysparamvaluetable GetPxsysparamvalueByQiyeIDAndValueID(Long qiyeID,Long ValueID);
}
