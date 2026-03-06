package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.SutClearVo;
import com.xwcloud.cloud.model.entity.Pxkeshiresettable;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-08
 */
public interface IPxkeshiresettableService extends IService<Pxkeshiresettable> {
    Page<SutClearVo> getClearPage(Page page, QueryWrapper queryWrapper);
    List<SutClearVo> ExporestuClear(QueryWrapper queryWrapper);
    String getClearkeshiMoney(Long stuID, Long qiyeID);
}
