package com.xwcloud.cloud.oa.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaLiushui;
import com.xwcloud.cloud.model.OA.Vo.LiushuiInfoVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-29
 */
public interface IOaLiushuiService extends IService<OaLiushui> {


    IPage<LiushuiInfoVo> getAllLiushuiInfo(Page<LiushuiInfoVo> page, @Param("ew") Wrapper wrapper);


    LiushuiInfoVo getOneLiushuiInfoById(Long id);
}
