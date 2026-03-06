package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.gonggaoVO;
import com.xwcloud.cloud.model.entity.Pxgonggaotable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-22
 */
public interface IPxgonggaotableService extends IService<Pxgonggaotable> {
    Page<gonggaoVO> GetgonggaoPages(Page page, QueryWrapper wrapper);
    List<gonggaoVO> GetgonggaoList(QueryWrapper wrapper);
}
