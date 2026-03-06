package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxliushuizhangtable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
public interface IPxliushuizhangtableService extends IService<Pxliushuizhangtable> {
    List<Pxliushuizhangtable> getstuliushui(Long stuID,Long qiyeID);
}
