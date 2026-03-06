package com.xwcloud.cloud.oa.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.Vo.PxcampusVo;
import com.xwcloud.cloud.model.entity.Pxcampustable;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-05
 */
public interface IPxcampustableService extends IService<Pxcampustable> {

    //分页获取所有的校区信息
    IPage<PxcampusVo> getAllPxcampusInfo(Page<PxcampusVo> page, @Param("ew") Wrapper wrapper);

    //根据id获取一条校区信息
    PxcampusVo getOnePxcampusById(Long id);
	
}
