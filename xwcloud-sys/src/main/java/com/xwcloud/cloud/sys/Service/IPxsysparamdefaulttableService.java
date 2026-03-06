package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxsysparamdefaulttable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-07
 */
public interface IPxsysparamdefaulttableService extends IService<Pxsysparamdefaulttable> {
    public int UpdateSysParamValue(String id,String value);
}
