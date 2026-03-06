package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxevaluationtable;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-29
 */
public interface IPxevaluationtableService extends IService<Pxevaluationtable> {
    Page<HashMap<String, String>> getPxevaluationtableJoinPage(Page page, Wrapper wrapper);
}
