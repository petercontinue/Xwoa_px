package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxclasstable;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-29
 */
public interface IPxclasstableService extends IService<Pxclasstable> {
    List<Pxclasstable> getNopkClass(Long qiyeID);
    List<HashMap<String,String>> getOnemonthnokeshiClass(Long qiyeID);
}
