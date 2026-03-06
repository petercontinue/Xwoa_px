package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.zuoyeVO;
import com.xwcloud.cloud.model.entity.Pxzuoyetable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-07
 */
public interface IPxzuoyetableService extends IService<Pxzuoyetable> {
    Page<zuoyeVO> GetAllstuZuoyeList(Page page, QueryWrapper wrapper);

    List<zuoyeVO> GetZuoyexiangqing(QueryWrapper wrapper);
}
