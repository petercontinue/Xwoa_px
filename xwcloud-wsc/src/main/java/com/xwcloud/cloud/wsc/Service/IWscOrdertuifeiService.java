package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.WscOrdertuifei;


import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-02-23
 */
public interface IWscOrdertuifeiService extends IService<WscOrdertuifei> {
	Page<HashMap<String, Object>> getTuihuokuanPage(Page page, QueryWrapper wrapper);
}
