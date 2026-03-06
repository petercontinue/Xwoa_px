package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.WscOrderVo;
import com.xwcloud.cloud.model.Vo.miaoshachenggongVO;
import com.xwcloud.cloud.model.entity.WscOrder;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
public interface IWscOrderService extends IService<WscOrder> {

    Page<WscOrderVo> getWscOrderPage(Page page, QueryWrapper wrapper);

    List<HashMap<String, Object>> getWscOrderList(QueryWrapper wrapper);

    Page<HashMap<String, Object>> GetsqleLiushuiPages(Page page, QueryWrapper wrapper);

    Page<HashMap<String, Object>> GetorderByUser(Page page, QueryWrapper wrapper);

    List<miaoshachenggongVO> GetmiaoshachenggongInfo(long qiyeID, long goodsID);

}
