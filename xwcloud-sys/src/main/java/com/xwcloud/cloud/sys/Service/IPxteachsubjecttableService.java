package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.teachSubjectVo;
import com.xwcloud.cloud.model.entity.Pxteachsubjecttable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-08
 */
public interface IPxteachsubjecttableService extends IService<Pxteachsubjecttable> {
    Page<teachSubjectVo> GetTeacheSubjectPages(Page page,String staffID, QueryWrapper wrapper);
}
