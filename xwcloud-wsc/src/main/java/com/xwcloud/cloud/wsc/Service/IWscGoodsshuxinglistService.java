package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.WscGoodsshuxinglist;
import com.xwcloud.cloud.model.entity.WscGoodsshuxinglistprice;

import com.xwcloud.cloud.model.Vo.WscGoodsAttributeVo;
import com.xwcloud.cloud.model.Vo.guigeshuxingVo;
import org.apache.ibatis.annotations.Param;

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
public interface IWscGoodsshuxinglistService extends IService<WscGoodsshuxinglist> {
    Page<WscGoodsAttributeVo> getGoodsAttributePage(Page page, QueryWrapper wrapper);

    List<guigeshuxingVo> GetGuigeList(QueryWrapper wrapper);

    WscGoodsshuxinglistprice getkucun(Wrapper wrapper);
    List<HashMap<String, Object>> GetAllKucun(QueryWrapper wrapper);
}
