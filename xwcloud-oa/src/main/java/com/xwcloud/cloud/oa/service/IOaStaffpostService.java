package com.xwcloud.cloud.oa.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaStaffpost;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-29
 */
public interface IOaStaffpostService extends IService<OaStaffpost> {
    Page<OaStaffpost> getAllStaffpostPages(Page page, QueryWrapper<OaStaffpost> queryWrapper);

    OaStaffpost getOneStaffpostByID(long id);
	
}
