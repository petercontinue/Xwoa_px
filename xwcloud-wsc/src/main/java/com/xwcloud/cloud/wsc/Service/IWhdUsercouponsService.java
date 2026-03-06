package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.WhdUsercoupons;


import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-25
 */
public interface IWhdUsercouponsService extends IService<WhdUsercoupons> {
    Page<HashMap<String, Object>> GetwscUsercouponsPage(Page page,QueryWrapper wrapper);
}
