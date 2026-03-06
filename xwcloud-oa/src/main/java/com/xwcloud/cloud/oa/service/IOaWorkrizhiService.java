package com.xwcloud.cloud.oa.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaWorkrizhi;
import com.xwcloud.cloud.model.OA.Vo.WorkrizhiVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-03
 */
public interface IOaWorkrizhiService extends IService<OaWorkrizhi> {

    //获取所有的workrizhi信息
    IPage<WorkrizhiVo> getAllWorkrizhiInfo(Page<WorkrizhiVo> page, Wrapper wrapper);

    WorkrizhiVo getOneWorkrizhiById(Long id);
}
