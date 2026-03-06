package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxsubjecttable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-10
 */
public interface IPxsubjecttableService extends IService<Pxsubjecttable> {
    List<Pxsubjecttable> getNoTeakemu(@Param("ew") QueryWrapper queryWrapper);
}
