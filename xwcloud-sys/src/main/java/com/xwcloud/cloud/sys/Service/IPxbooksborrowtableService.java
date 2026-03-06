package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxbooksborrowtable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-30
 */
public interface IPxbooksborrowtableService extends IService<Pxbooksborrowtable> {
    String getnobreakList(QueryWrapper queryWrapper);
}
