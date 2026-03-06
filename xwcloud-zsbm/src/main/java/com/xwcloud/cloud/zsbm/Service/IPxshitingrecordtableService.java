package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.shitingLiushuiVo;
import com.xwcloud.cloud.model.entity.Pxshitingrecordtable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
public interface IPxshitingrecordtableService extends IService<Pxshitingrecordtable> {
    List<Pxshitingrecordtable> GetShitingRecordsByStuID(long stuID);

    int deleteShitongRecordsByStuID(long stuID);

    Page<shitingLiushuiVo> GetShitingLiushuiPages(Page<shitingLiushuiVo> page, QueryWrapper<shitingLiushuiVo> wrapper);

    List<shitingLiushuiVo> GetShitingLiushuiList(QueryWrapper wrapper);
}
