package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxoldschooltable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
public interface IPxoldschooltableService extends IService<Pxoldschooltable> {
    Pxoldschooltable GetOldSchoolByName(String oldSchoolName);
}
