package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.WscOrderGoodsVo;
import com.xwcloud.cloud.model.entity.WscOrdergoods;

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
public interface IWscOrdergoodsService extends IService<WscOrdergoods> {
	Page<WscOrderGoodsVo> getWscOrderGoodsPage(Page page, QueryWrapper wrapper);
	Page<HashMap<String, Object>> getOrderGoodsDetailByOrderNumberPage(Page page, QueryWrapper wrapper);
	Page<HashMap<String, Object>> GetAllOrderPingjiaPage(Page page,QueryWrapper wrapper);
	List<HashMap<String,Object>> getonegoodpingjia(QueryWrapper queryWrapper);
}
