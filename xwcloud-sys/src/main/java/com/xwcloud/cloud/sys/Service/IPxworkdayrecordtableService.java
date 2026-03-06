package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.workdayrecordVo;
import com.xwcloud.cloud.model.entity.Pxworkdayrecordtable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-25
 */
public interface IPxworkdayrecordtableService extends IService<Pxworkdayrecordtable> {
	public Page<workdayrecordVo> Getworkdayrecords(Page page, QueryWrapper wrapper);

	List<workdayrecordVo> GetWorkdayrecordsList( QueryWrapper wrapper);
}
