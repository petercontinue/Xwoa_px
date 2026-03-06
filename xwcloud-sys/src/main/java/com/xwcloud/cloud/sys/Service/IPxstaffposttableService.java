package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.cxStaffpostVO;
import com.xwcloud.cloud.model.Vo.staffpostVo;
import com.xwcloud.cloud.model.entity.Pxstaffposttable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yinqi
 * @since 2020-10-20
 */
public interface IPxstaffposttableService extends IService<Pxstaffposttable> {

	public Page<staffpostVo> getStaffpostList(Page page, QueryWrapper wrapper);


	List<Pxstaffposttable> getAllList();

	List<staffpostVo> GetStaffPostListByCampusID(long campusID);

	List<cxStaffpostVO> GetSearchStaffPostList(long qiyeID);
}
