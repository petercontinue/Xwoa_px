package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.WscTuikelevel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-19
 */
public interface IWscTuikelevelService extends IService<WscTuikelevel> {
    Page<WscTuikelevel> getpage(Page page, QueryWrapper queryWrapper);
}
