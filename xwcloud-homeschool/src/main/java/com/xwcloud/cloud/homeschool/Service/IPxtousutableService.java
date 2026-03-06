package com.xwcloud.cloud.homeschool.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.PxstuFeedbackVo;
import com.xwcloud.cloud.model.entity.Pxtousutable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
public interface IPxtousutableService extends IService<Pxtousutable> {
	Page<PxstuFeedbackVo> getPage(Page page, Wrapper wrapper);
	List<PxstuFeedbackVo> getJoinList(Wrapper wrapper);
}
