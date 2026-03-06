package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.PxworkweekrecordVo;
import com.xwcloud.cloud.model.entity.Pxworkweekrecordtable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-25
 */
public interface IPxworkweekrecordtableService extends IService<Pxworkweekrecordtable> {
    Page<PxworkweekrecordVo> Getworkweekrecords(Page page, QueryWrapper wrapper);

    List<PxworkweekrecordVo> getworkweekrecordsList(QueryWrapper wrapper);
}
