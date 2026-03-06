package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.PxsalarystyletableVo;
import com.xwcloud.cloud.model.entity.Pxsalarystyletable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-25
 */
public interface IPxsalarystyletableService extends IService<Pxsalarystyletable> {
    Page<PxsalarystyletableVo> getPage(Page page, Wrapper wrapper);

}
