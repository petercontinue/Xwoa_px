package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.shenpiVo;
import com.xwcloud.cloud.model.entity.Tuifeishenpi;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-08
 */
public interface ITuifeishenpiService extends IService<Tuifeishenpi> {
    Page<shenpiVo> getshenpiPage(Page page, QueryWrapper queryWrapper);
}
