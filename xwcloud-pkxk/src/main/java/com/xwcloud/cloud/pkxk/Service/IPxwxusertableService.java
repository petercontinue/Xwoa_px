package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxwxusertable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-14
 */
public interface IPxwxusertableService extends IService<Pxwxusertable> {
    List<Pxwxusertable> getuserList(Long stuID,Long qiyeID);
	
}
