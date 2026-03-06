package com.xwcloud.cloud.oa.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaYingjianbuyrecord;
import com.xwcloud.cloud.model.OA.Vo.YingjianInfoVo;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-03
 */
public interface IOaYingjianbuyrecordService extends IService<OaYingjianbuyrecord> {

    //分页查询所有的硬件下单信息
    IPage<YingjianInfoVo> getAllYingjianrecordInfo(Page<YingjianInfoVo> page, @Param("ew") Wrapper wrapper);


    YingjianInfoVo getOneYingjianrecordById(Long id);

}
