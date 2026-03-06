package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxwxusertable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-05
 */
public interface IPxwxusertableService extends IService<Pxwxusertable> {
    Pxwxusertable GetWxuserByStaffID(String staffID);
}
