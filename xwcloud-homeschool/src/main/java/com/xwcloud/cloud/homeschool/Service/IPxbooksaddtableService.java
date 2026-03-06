package com.xwcloud.cloud.homeschool.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.PxbooksaddtableVo;
import com.xwcloud.cloud.model.entity.Pxbooksaddtable;


import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-13
 */
public interface IPxbooksaddtableService extends IService<Pxbooksaddtable> {
	Page<PxbooksaddtableVo> getPage(Page page, Wrapper wrapper);
	List<PxbooksaddtableVo> getJoinList(Wrapper wrapper);
	List<HashMap<String, String>> gettestlist();
}
