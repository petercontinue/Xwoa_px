package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxpaymoneystyletable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-22
 */
public interface IPxpaymoneystyletableService extends IService<Pxpaymoneystyletable> {
    List<Pxpaymoneystyletable> GetAllPaymoneystyleList(Long qiyeID);
}
