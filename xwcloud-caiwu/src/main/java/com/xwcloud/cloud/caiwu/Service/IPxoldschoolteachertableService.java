package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxoldschoolteachertable;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-02
 */
public interface IPxoldschoolteachertableService extends IService<Pxoldschoolteachertable> {
	Page<HashMap<String,String>> getOldteacherPage(Page page, Wrapper wrapper);
	List<HashMap<String,String>> getOldteacherBili(Wrapper wrapper);
}
