package com.xwcloud.cloud.homeschool.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxsysparamvaluetable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-07
 */
public interface IPxsysparamvaluetableService extends IService<Pxsysparamvaluetable> {
    List<Pxsysparamvaluetable> selectsysvalue(QueryWrapper queryWrapper);

    Pxsysparamvaluetable getsysvalue(Long qiyeID, Long sysparamTypeID);
}
