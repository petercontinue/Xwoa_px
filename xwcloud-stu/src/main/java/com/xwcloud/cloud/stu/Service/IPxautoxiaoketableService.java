package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxautoxiaoketable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
public interface IPxautoxiaoketableService extends IService<Pxautoxiaoketable> {
    List<Pxautoxiaoketable> getbyauto(Long buxiID,Long qiyeID);
}
