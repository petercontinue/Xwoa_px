package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxstugradetable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-08
 */
public interface IPxstugradetableService extends IService<Pxstugradetable> {
    Pxstugradetable getOne(Long qiyeID);
}
