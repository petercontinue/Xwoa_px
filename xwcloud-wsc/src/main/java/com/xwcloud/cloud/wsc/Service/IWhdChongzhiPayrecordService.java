package com.xwcloud.cloud.wsc.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.WhdChongzhiPayrecordVo;
import com.xwcloud.cloud.model.entity.WhdChongzhiPayrecord;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
public interface IWhdChongzhiPayrecordService extends IService<WhdChongzhiPayrecord> {
	Page<WhdChongzhiPayrecordVo> getWhdChongzhiPayRecordPage(Page Page, QueryWrapper wrapper);
}
