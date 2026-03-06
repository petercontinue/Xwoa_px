package com.xwcloud.cloud.homeschool.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxzuoyetable;
import com.xwcloud.cloud.model.Vo.PxzuoyetableVo;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
public interface IPxzuoyetableService extends IService<Pxzuoyetable> {

	Page<PxzuoyetableVo> getPage(Page<PxzuoyetableVo> page , Wrapper wrapper);
	List<PxzuoyetableVo> getJoinList(Wrapper wrapper);
	Page<HashMap<String,String>> getZuoyeDetaile(Page page,Wrapper wrapper);
}
