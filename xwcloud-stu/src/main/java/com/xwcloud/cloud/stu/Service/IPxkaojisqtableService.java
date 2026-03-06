package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.kjSqVo;
import com.xwcloud.cloud.model.entity.Pxkaojisqtable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
public interface IPxkaojisqtableService extends IService<Pxkaojisqtable> {
    Page<kjSqVo> getKJsqPage(Page page,QueryWrapper queryWrapper);
}
