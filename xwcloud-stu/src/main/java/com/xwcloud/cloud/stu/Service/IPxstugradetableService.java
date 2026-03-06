package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxstugradetable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-03
 */
public interface IPxstugradetableService extends IService<Pxstugradetable> {
    Pxstugradetable getOne(Long qiyeID);
    List<Pxstugradetable> selectstuGrade(QueryWrapper queryWrapper);
}
