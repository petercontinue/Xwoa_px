package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxczhuodongtable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-12
 */
public interface IPxczhuodongtableService extends IService<Pxczhuodongtable> {
    List<Pxczhuodongtable> GetChongzhiuhuodongByDate(String Date,long qiyeID);
}
