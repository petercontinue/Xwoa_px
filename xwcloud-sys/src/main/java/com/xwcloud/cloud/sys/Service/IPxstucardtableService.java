package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxstucardtable;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-29
 */
public interface IPxstucardtableService extends IService<Pxstucardtable> {
    List<Pxstucardtable> getNostuCardlist(QueryWrapper queryWrapper);
}
