package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxjifentable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-18
 */
public interface IPxjifentableService extends IService<Pxjifentable> {
    Integer deleteJiifenByStuID(Long stuID,long qiyeID);
}
