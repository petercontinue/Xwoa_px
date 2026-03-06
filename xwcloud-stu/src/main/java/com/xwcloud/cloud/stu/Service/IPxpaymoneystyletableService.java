package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxpaymoneystyletable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-05
 */
public interface IPxpaymoneystyletableService extends IService<Pxpaymoneystyletable> {
    List<Pxpaymoneystyletable> getOnePay(Long qiyeID, String moneystyleName);
}
