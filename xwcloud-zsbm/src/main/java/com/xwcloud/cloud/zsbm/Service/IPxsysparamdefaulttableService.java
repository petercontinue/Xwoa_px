package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxsysparamdefaulttable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
public interface IPxsysparamdefaulttableService extends IService<Pxsysparamdefaulttable> {
    Pxsysparamdefaulttable GetSysParamById(long Id);
}
