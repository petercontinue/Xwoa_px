package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.jinxiaocunXSliushuiVo;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliesorderstable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-21
 */
public interface IPxteachingsuppliesorderstableService extends IService<Pxteachingsuppliesorderstable> {
    Page<jinxiaocunXSliushuiVo> getXiaoshouLiushuiDays(Page page, QueryWrapper wrapper);

    Page<jinxiaocunXSliushuiVo> GetTodayXiaoshouliushui(Page page,long qiyeID);

    List<jinxiaocunXSliushuiVo> GetAllXiaoshouliushuiList(QueryWrapper wrapper);
}
