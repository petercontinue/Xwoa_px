package com.xwcloud.cloud.homeschool.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.PxqingjiatableVo;
import com.xwcloud.cloud.model.entity.Pxqingjiatable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
public interface IPxqingjiatableService extends IService<Pxqingjiatable> {
	Page<PxqingjiatableVo> getPage(Page<PxqingjiatableVo> page, Wrapper wrapper);
	List<PxqingjiatableVo> getJoinList(Wrapper wrapper);
}
