package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxstukaoqingteachertable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-18
 */
public interface IPxstukaoqingteachertableService extends IService<Pxstukaoqingteachertable> {
    int removeBystukaoqingTabID(Long stukaoqingTabID);
}
