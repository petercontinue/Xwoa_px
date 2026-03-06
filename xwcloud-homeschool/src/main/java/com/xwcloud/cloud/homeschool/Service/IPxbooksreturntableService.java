package com.xwcloud.cloud.homeschool.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.PxbooksreturntableVo;
import com.xwcloud.cloud.model.entity.Pxbooksreturntable;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-13
 */
public interface IPxbooksreturntableService extends IService<Pxbooksreturntable> {
	Page<PxbooksreturntableVo> getPage(Page page, Wrapper wrapper);
	List<PxbooksreturntableVo> getJoinList(Wrapper wrapper);
}
