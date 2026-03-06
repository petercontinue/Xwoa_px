package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxstuparamvaluetable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
public interface IPxstuparamvaluetableService extends IService<Pxstuparamvaluetable> {
    List<Pxstuparamvaluetable> getstuParam(Long tid, Long stuID,Long qiyeID);
    List<Pxstuparamvaluetable> selectstuparamvalue(QueryWrapper queryWrapper);
}
