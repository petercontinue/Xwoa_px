package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.daofangVo;
import com.xwcloud.cloud.model.entity.Pxyxinvitedaofangtable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
public interface IPxyxinvitedaofangtableService extends IService<Pxyxinvitedaofangtable> {
    Page<daofangVo> GetinvitationDaofangByStuIDPages(Page<daofangVo> page, QueryWrapper<daofangVo> wrapper);
}
