package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxstafftable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-08
 */
public interface IPxstafftableService extends IService<Pxstafftable> {
    List<listVo> getallStaff(QueryWrapper queryWrapper);
}
