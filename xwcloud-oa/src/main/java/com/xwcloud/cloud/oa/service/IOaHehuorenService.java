package com.xwcloud.cloud.oa.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaHehuoren;
import com.xwcloud.cloud.model.OA.Vo.HehuorenVo;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-02
 */
public interface IOaHehuorenService extends IService<OaHehuoren> {

    //分页获取所有的合伙人信息
    IPage<HehuorenVo> getAllHehuorenInfo(Page<HehuorenVo> page, Wrapper wrapper);

    //获取一个合伙人信息
    HehuorenVo getOneHehuorenById(Long id);
}
