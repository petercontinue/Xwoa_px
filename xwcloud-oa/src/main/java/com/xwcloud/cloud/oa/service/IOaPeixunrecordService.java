package com.xwcloud.cloud.oa.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaPeixunrecord;
import com.xwcloud.cloud.model.OA.Vo.PeixunrecordVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-02
 */
public interface IOaPeixunrecordService extends IService<OaPeixunrecord> {

    //分页获取所有的培训记录信息
    IPage<PeixunrecordVo> getAllPeixunrecordInfo(Page<PeixunrecordVo> page, @Param("ew")Wrapper wrapper);

    //根据id获取一条培训记录信息
    PeixunrecordVo getOnePeixunrecordById(Long id);
}
