package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.yuexiqokeVo;
import com.xwcloud.cloud.model.entity.Pxchongzhipaytable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-20
 */
public interface IPxchongzhipaytableService extends IService<Pxchongzhipaytable> {
    Page<yuexiqokeVo> getyuexiaokePage(Page page, QueryWrapper queryWrapper);
    List<Pxchongzhipaytable> selectChongzhiPay(QueryWrapper queryWrapper);
    List<yuexiqokeVo> ExportyuexiaokePage(QueryWrapper queryWrapper);
}
