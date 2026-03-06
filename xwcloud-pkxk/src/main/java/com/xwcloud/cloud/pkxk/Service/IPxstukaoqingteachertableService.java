package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.entity.Pxstukaoqingteachertable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
public interface IPxstukaoqingteachertableService extends IService<Pxstukaoqingteachertable> {
    List<Pxstukaoqingteachertable> selectstukaoteacherqing(@Param("ew") QueryWrapper queryWrapper);
}
