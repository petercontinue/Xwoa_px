package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxtskaiguandefaulttable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-07
 */
public interface IPxtskaiguandefaulttableService extends IService<Pxtskaiguandefaulttable> {
    public int UpdateTsKaiGuan(String value,String id );
}
