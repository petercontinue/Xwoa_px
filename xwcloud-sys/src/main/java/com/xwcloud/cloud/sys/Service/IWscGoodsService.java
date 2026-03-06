package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.WscGoods;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-29
 */
public interface IWscGoodsService extends IService<WscGoods> {
    List<HashMap<String, Object>> getnoKCGoods(QueryWrapper queryWrapper);
}
