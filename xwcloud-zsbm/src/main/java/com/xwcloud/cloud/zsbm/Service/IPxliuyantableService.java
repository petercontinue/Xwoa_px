package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxliuyantable;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-25
 */
public interface IPxliuyantableService extends IService<Pxliuyantable> {
    Page<Pxliuyantable> GetAllLiuyanPages(Page page, QueryWrapper wrapper);
}
