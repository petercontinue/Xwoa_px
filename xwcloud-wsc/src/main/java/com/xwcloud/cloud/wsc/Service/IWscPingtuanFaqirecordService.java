package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.WscPingtuanFaqiRecordVo;
import com.xwcloud.cloud.model.entity.WscPingtuanFaqirecord;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
public interface IWscPingtuanFaqirecordService extends IService<WscPingtuanFaqirecord> {
	Page<WscPingtuanFaqiRecordVo> getWscPingtuanFaqiPage(Page page, QueryWrapper queryWrapper);
	List<WscPingtuanFaqirecord> GetFaqipingtuanlist(long wscgoodsID);

	List<WscPingtuanFaqirecord> GetFaqipingtuanSuccesslist(long wscgoodsID);
}
