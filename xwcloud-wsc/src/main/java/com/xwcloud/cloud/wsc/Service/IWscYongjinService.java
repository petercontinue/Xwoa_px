package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.WscYongjin;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-02-22
 */
public interface IWscYongjinService extends IService<WscYongjin> {
    Page<HashMap<String, Object>> getfangyongShowPages(Page page, QueryWrapper wrapper);
    Page<HashMap<String,Object>> getbuytuikePages(Page page , QueryWrapper queryWrapper);
    Page<HashMap<String,Object>> gettkteamPage(Page page ,QueryWrapper queryWrapper);
    Page<HashMap<String,Object>> getfanyongpaiming(Page page , QueryWrapper queryWrapper);
}
