package com.xwcloud.cloud.homeschool.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.PxgonggaojiazhangtableVo;
import com.xwcloud.cloud.model.entity.Pxgonggaojiazhangtable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-12
 */
public interface IPxgonggaojiazhangtableService extends IService<Pxgonggaojiazhangtable> {
	Page<PxgonggaojiazhangtableVo> getPage(Page page, Wrapper wrapper);
    List<PxgonggaojiazhangtableVo> getJoinList( Wrapper wrapper);
}
