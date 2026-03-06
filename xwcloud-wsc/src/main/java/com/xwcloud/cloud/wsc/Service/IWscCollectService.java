package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.collectVo;
import com.xwcloud.cloud.model.entity.WscCollect;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-26
 */
public interface IWscCollectService extends IService<WscCollect> {
    Page<collectVo> GetwscUserCollect(Page page, QueryWrapper wrapper);
}
