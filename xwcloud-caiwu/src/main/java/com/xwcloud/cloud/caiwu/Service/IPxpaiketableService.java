package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxpaiketable;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-15
 */
public interface IPxpaiketableService extends IService<Pxpaiketable> {
    Page<HashMap<String,String>> getStupaikePage(Page page, Wrapper wrapper);
    Page<HashMap<String,String>>getTeacherpaikePage(Page page, Wrapper wrapper);
    Page<HashMap<String,String>>getCampuspaikePage(Page page, Wrapper wrapper);
    Page<HashMap<String,String>>getClasspaikePage(Page page,Wrapper wrapper);
	
}
