package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxdropdownoptionstable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-05
 */
public interface IPxdropdownoptionstableService extends IService<Pxdropdownoptionstable> {
	public List<Pxdropdownoptionstable> GetOptionsById(String stuParamTypeId);
}
