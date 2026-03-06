package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.log.Logxjbtable;
import com.xwcloud.cloud.model.Vo.LogxjbVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-25
 */
public interface ILogxjbtableService extends IService<Logxjbtable> {
    Page<LogxjbVo> getLogxjbInfo(Page page, QueryWrapper wrapper);
    List<LogxjbVo> getLogxjbInfolist(QueryWrapper queryWrapper);
}
