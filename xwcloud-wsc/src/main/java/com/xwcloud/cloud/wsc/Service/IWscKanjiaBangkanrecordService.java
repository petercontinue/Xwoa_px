package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.WscKanHelpRecordVo;
import com.xwcloud.cloud.model.entity.WscKanjiaBangkanrecord;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
public interface IWscKanjiaBangkanrecordService extends IService<WscKanjiaBangkanrecord> {
	Page<WscKanHelpRecordVo> getWscKanHelpRecordPage(Page page, QueryWrapper wrapper);
	List<WscKanjiaBangkanrecord> GetBangkanRecords(long kanjiaGoodsID);
}
