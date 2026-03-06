package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.WscKanjiaFaqiRecordVo;
import com.xwcloud.cloud.model.Vo.faqikanjiaVO;
import com.xwcloud.cloud.model.entity.WscKanjiaFaqirecord;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
public interface IWscKanjiaFaqirecordService extends IService<WscKanjiaFaqirecord> {
	Page<WscKanjiaFaqiRecordVo> getWscKanjiaFaqiRecordPage(Page page, QueryWrapper wrapper);

	List<WscKanjiaFaqirecord> GetfaqiKanjiaInfoByGoodsID(long kanjiaGoodsID);
	List<WscKanjiaFaqirecord> GetfaqiKanjiaSuccessInfoByGoodsID(long kanjiaGoodsID);
	List<faqikanjiaVO> panduanDangqianweixinyonghu(long wscUserID, long goodsID);
}
