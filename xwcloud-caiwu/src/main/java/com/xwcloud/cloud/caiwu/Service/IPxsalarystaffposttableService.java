package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.PxsalarystaffposttableVo;
import com.xwcloud.cloud.model.entity.Pxsalarystaffposttable;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-25
 */
public interface IPxsalarystaffposttableService extends IService<Pxsalarystaffposttable> {
	Page<PxsalarystaffposttableVo> getPage(Page page, Wrapper wrapper);
	List<PxsalarystaffposttableVo> getJoinList(Wrapper wrapper);
	List<HashMap<String,String>> getGongzixiangmuList(Wrapper wrapper);
}
