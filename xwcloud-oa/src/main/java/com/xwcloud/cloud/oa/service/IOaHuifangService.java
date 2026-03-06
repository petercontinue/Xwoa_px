package com.xwcloud.cloud.oa.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaHuifang;
import com.xwcloud.cloud.model.OA.Vo.HuifangVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-03
 */
public interface IOaHuifangService extends IService<OaHuifang> {

    //分页获取所有回访记录
    IPage<HuifangVo> getAllHuifangInfo(Page<HuifangVo> page);

    //根据id获取一条回访记录
    HuifangVo getOneHuifangById(Long id);
}
