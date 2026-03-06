package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.WhdChongzhiRecordVo;
import com.xwcloud.cloud.model.entity.WhdChongzhiRecord;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
public interface IWhdChongzhiRecordService extends IService<WhdChongzhiRecord> {
	Page<WhdChongzhiRecordVo> getWhdChongzhiRecordPage(Page page, QueryWrapper wrapper);
}
