package com.xwcloud.cloud.oa.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.WscOrder;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-25
 */
public interface IWscOrderService extends IService<WscOrder> {
    List<WscOrder> updateOrdercampus(String cxStr, Long id);
}
