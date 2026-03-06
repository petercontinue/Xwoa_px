package com.xwcloud.cloud.homeschool.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.PxbooksborrowtableVo;
import com.xwcloud.cloud.model.entity.Pxbooksborrowtable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-13
 */
public interface IPxbooksborrowtableService extends IService<Pxbooksborrowtable> {
	Page<PxbooksborrowtableVo> getPage(Page page, Wrapper wrapper);
	List<PxbooksborrowtableVo> getJoinList(Wrapper wrapper);

}
