package com.xwcloud.cloud.oa.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaQiandanXufeiSp;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-25
 */
public interface IOaQiandanXufeiSpService extends IService<OaQiandanXufeiSp> {
    Page<HashMap<String,Object>> getxfQiandanSpInfo(Page page, Wrapper wrapper);
}
