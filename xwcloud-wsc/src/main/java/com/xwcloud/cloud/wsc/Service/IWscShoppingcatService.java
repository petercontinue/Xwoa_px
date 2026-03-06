package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.WscShoppingcat;


import java.util.HashMap;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-02-23
 */
public interface IWscShoppingcatService extends IService<WscShoppingcat> {
    Page<HashMap<String, Object>> getShoppingCatPage(Page page, QueryWrapper wrapper);

    Page<HashMap<String, Object>> getshoppingcartByApp(Page page, QueryWrapper wrapper);
}
