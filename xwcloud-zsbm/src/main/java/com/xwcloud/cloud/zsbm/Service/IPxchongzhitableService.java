package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.chongzhiListVo;
import com.xwcloud.cloud.model.Vo.chongzhiliushuiVO;
import com.xwcloud.cloud.model.entity.Pxchongzhitable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-12
 */
public interface IPxchongzhitableService extends IService<Pxchongzhitable> {
	public Page<chongzhiListVo> GetUserChongzhiListPages(Page page, QueryWrapper wrapper);

	List<chongzhiListVo> GetUserChongzhiListList(QueryWrapper wrapper);

	Page<chongzhiliushuiVO> GetChongzhiliushuiPages(Page page, QueryWrapper wrapper);

}
