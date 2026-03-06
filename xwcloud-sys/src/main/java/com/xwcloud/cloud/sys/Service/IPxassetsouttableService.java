package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.assetAddVO;
import com.xwcloud.cloud.model.entity.Pxassetsouttable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-22
 */
public interface IPxassetsouttableService extends IService<Pxassetsouttable> {
    Page<assetAddVO> GetassetsOutPages(Page page, QueryWrapper wrapper);
}
