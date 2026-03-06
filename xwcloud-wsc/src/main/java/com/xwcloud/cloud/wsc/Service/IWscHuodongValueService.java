package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.WscHuodongValueVo;
import com.xwcloud.cloud.model.entity.WscHuodongValue;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
public interface IWscHuodongValueService extends IService<WscHuodongValue> {
	Page<WscHuodongValueVo> getWscHuodongValPage(Page page, QueryWrapper wrapper);
}
