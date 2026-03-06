package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.PxsalarytableVo;
import com.xwcloud.cloud.model.entity.Pxsalarystyletable;
import com.xwcloud.cloud.model.entity.Pxsalarytable;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-17
 */
public interface IPxsalarytableService extends IService<Pxsalarytable> {
	Page<PxsalarytableVo> getPage(Page page, Wrapper wrapper);
	PxsalarytableVo getGongzi(Wrapper wrapper);
	List<PxsalarytableVo> getJoinList(Wrapper wrapper);
	List<Pxsalarystyletable> getGongziPro(Wrapper wrapper);
	List<HashMap<String,String>> getGongzitiao(Wrapper wrapper, List<String> stylelist);

	List<PxsalarytableVo> GetSalaryList(QueryWrapper wrapper);
}
