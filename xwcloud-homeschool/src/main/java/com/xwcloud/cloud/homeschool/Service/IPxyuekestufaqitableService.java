package com.xwcloud.cloud.homeschool.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxyuekestufaqitable;
import com.xwcloud.cloud.model.Vo.PxyuekestufaqitableVo;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
public interface IPxyuekestufaqitableService extends IService<Pxyuekestufaqitable> {
	Page<PxyuekestufaqitableVo> getPage(Page page, Wrapper wrapper);
	List<PxyuekestufaqitableVo> getJoinList(Wrapper wrapper);
	List<HashMap<String,String>> getClassRoomList(Wrapper wrapper);
}
