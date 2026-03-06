package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.WhdCoupons;


import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-25
 */
public interface IWhdCouponsService extends IService<WhdCoupons> {
    Page<HashMap<String, Object>> GetCouponsPages(Page page, QueryWrapper wrapper);
}
