package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxoldschooltable;

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
public interface IPxoldschooltableService extends IService<Pxoldschooltable> {
	Page<HashMap<String,String>> getOldschoolDetail(Page page , Wrapper wrapper);
	List<HashMap<String,String>> getOldschoolBili(Wrapper wrapper);
}
