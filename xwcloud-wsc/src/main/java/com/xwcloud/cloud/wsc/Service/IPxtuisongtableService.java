package com.xwcloud.cloud.wsc.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.messageVO;
import com.xwcloud.cloud.model.entity.Pxtuisongtable;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-21
 */
public interface IPxtuisongtableService extends IService<Pxtuisongtable> {
    Page<messageVO> GetAllMessagePages(Page page, QueryWrapper wrapper);

    Page<messageVO> GetAllMessageStaffPages(Page page, @Param("ew") Wrapper wrapper);
}
