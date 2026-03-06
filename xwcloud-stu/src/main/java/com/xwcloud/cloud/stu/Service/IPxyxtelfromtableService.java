package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxyxtelfromtable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-05
 */
public interface IPxyxtelfromtableService extends IService<Pxyxtelfromtable> {
    List<Pxyxtelfromtable> getOneTelfrom(Long qiyeID);
	
}
