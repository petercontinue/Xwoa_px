package com.xwcloud.cloud.oa.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.OA.OaLiushuiYewu;
import com.xwcloud.cloud.model.OA.Vo.YewuLiushuiVo;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-13
 */
public interface IOaLiushuiYewuService extends IService<OaLiushuiYewu> {

    //获取所有的业务流水信息
    IPage<YewuLiushuiVo> getAllYewuLiushuiInfo(Page<YewuLiushuiVo> page, @Param("ew") Wrapper wrapper);

}
