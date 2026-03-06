package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxdropdownoptionstable;

import com.xwcloud.cloud.model.Vo.listVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-08
 */
public interface IPxdropdownoptionstableService extends IService<Pxdropdownoptionstable> {
    List<listVo> getparamTypeList(Long stuParamTypeId , Long qiyeID);
}
