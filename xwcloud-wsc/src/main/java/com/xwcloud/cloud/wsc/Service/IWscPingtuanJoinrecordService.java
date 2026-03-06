package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.WscPingtuanJoinRecordVo;
import com.xwcloud.cloud.model.entity.WscPingtuanJoinrecord;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
public interface IWscPingtuanJoinrecordService extends IService<WscPingtuanJoinrecord> {
	Page<WscPingtuanJoinRecordVo> getWscPingtuanJoinRecordPage(Page page, QueryWrapper wrapper);
	List<WscPingtuanJoinrecord> GetpingtuanJoinList(long wscgoodsID);
}
