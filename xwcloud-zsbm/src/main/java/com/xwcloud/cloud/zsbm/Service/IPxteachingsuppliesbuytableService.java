package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.caigoushenqingVo;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliesbuytable;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
public interface IPxteachingsuppliesbuytableService extends IService<Pxteachingsuppliesbuytable> {
    Page<caigoushenqingVo> GetTeachingSuppliesbuyPages(Page page, QueryWrapper wrapper);
}
