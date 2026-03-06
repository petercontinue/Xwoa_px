package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.WscGoodsVo;
import com.xwcloud.cloud.model.entity.WscGoods;
import com.xwcloud.cloud.model.entity.WscGoodsshuxinglistpricePingtuan;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
public interface IWscGoodsService extends IService<WscGoods> {
	Page<WscGoodsVo> getWscGoodsPage(Page page, QueryWrapper wrapper);

	List<WscGoodsVo> GetAllGoodsListByHuodongID(QueryWrapper wrapper);

	List<WscGoodsshuxinglistpricePingtuan> getshuxinglistpingtuanPricebygoodsID(long goodsID);

	List<HashMap<String, Object>> GetGoodsDetail(String goodsID);
}
