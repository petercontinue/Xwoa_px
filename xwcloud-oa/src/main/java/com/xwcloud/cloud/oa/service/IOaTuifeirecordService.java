package com.xwcloud.cloud.oa.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaTuifeirecord;
import com.xwcloud.cloud.model.OA.Vo.TuifeirecordVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-01
 */
public interface IOaTuifeirecordService extends IService<OaTuifeirecord> {

    //往退费记录表里插入一条记录
    boolean addTuifeiReecord(OaTuifeirecord oaTuifeirecord);

    //分页查询所有的退费记录
    IPage<TuifeirecordVo> getAllTuifeirecordInfo(Page<TuifeirecordVo> page, @Param("ew") Wrapper wrapper);

    //根据id查询一条退费记录
    TuifeirecordVo getOneTuifeirecordById(Long id);
}
