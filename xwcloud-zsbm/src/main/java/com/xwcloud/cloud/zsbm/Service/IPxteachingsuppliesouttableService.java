package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.outjiluVo;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliesouttable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-14
 */
public interface IPxteachingsuppliesouttableService extends IService<Pxteachingsuppliesouttable> {
    Page<outjiluVo> GetTeachingSuppliesOutPages(Page page, QueryWrapper wrapper);

    List<outjiluVo> GetTeachingSuppliesOutList(QueryWrapper wrapper);
}
