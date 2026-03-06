package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.xflvVo;
import com.xwcloud.cloud.model.entity.Pxkeshistuteachertable;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-02
 */
public interface IPxkeshistuteachertableService extends IService<Pxkeshistuteachertable> {
	Page<HashMap<String,String>> getRenewPage(Page page , Wrapper wrapper);
	Page<xflvVo> getallshuju(Page page, QueryWrapper queryWrapper,QueryWrapper queryWrapper1);
}
