package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.WscDongtaiinfo;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-06
 */
public interface IWscDongtaiinfoService extends IService<WscDongtaiinfo> {
    Page<HashMap<String, String>> getAllWscUserDongtaiPages(Page page, QueryWrapper wrapper);

    Page<HashMap<String,String>> GetPagesDianzanInfo(Page page,QueryWrapper wrapper);

    Page<HashMap<String,String>> GetPagesPinglunInfos(Page page,QueryWrapper queryWrapper);
}
