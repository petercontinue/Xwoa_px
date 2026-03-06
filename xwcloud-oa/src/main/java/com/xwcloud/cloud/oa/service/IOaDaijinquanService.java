package com.xwcloud.cloud.oa.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaDaijinquan;
import com.xwcloud.cloud.model.OA.Vo.DaijinquanVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-02
 */
public interface IOaDaijinquanService extends IService<OaDaijinquan> {

    //获取所有的代金券信息
    IPage<DaijinquanVo> getAllDaijinquanInfo(Page<DaijinquanVo> page, @Param("ew") Wrapper wrapper);

    //根据id获取一个代金券信息
    DaijinquanVo getOneDaijinquanById(Long id);
}
