package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxyxqiandantable;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-01
 */
public interface IPxyxqiandantableService extends IService<Pxyxqiandantable> {
	Page<HashMap<String,String>> getYixiangDetail(Page page, Wrapper wrapper);
	Page<HashMap<String,String>> getYixiangPage(Page page,Wrapper wrapper);
	List<HashMap<String,Object>> getYixiangList(Wrapper wrapper);
}
