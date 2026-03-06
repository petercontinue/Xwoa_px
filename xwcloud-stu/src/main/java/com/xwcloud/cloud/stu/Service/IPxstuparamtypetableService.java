package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxstuparamtypetable;

import com.xwcloud.cloud.model.Vo.pramaTypeVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-08
 */
public interface IPxstuparamtypetableService extends IService<Pxstuparamtypetable> {
    List<Pxstuparamtypetable> selectstuparamtype(QueryWrapper queryWrapper);
    List<pramaTypeVo> getByqiye(Long stuID,Long qiyeID);
    List<pramaTypeVo> getOne(Long qiyeID);
}
