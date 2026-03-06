package com.xwcloud.cloud.homeschool.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.PxbookstableVo;
import com.xwcloud.cloud.model.entity.Pxbookstable;
import com.xwcloud.cloud.model.entity.Pxcampustable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-13
 */
public interface IPxbookstableService extends IService<Pxbookstable> {
	Page<PxbookstableVo> getPage(Page page, Wrapper wrapper);
	List<PxbookstableVo> getJoinList(Wrapper wrapper);
	List<Pxcampustable> getCampusList(Wrapper wrapper);
}
