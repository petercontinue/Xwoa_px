package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-31
 */
public interface IPxbuxikechengtableService extends IService<Pxbuxikechengtable> {
    String getstuInClass(String stuID,Long qiyeID);
}
