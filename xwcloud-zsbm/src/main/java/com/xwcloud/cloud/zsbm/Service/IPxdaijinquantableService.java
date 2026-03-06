package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxdaijinquantable;
import com.xwcloud.cloud.model.zsbm.Vo.daijinquanVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-14
 */
public interface IPxdaijinquantableService extends IService<Pxdaijinquantable> {
    Pxdaijinquantable GetDaijinquanByID(Long qiandanID);

    List<Pxdaijinquantable> GetDaijinquanListByStuID(Long stuID);

    Integer DeleteDaijinquanByStuID(Long stuID);

    Page<daijinquanVo> GetDaijinquanLiushuiPages(Page page, QueryWrapper wrapper);

    List<daijinquanVo> GetDaijinquanLiushuiList(QueryWrapper wrapper);
}
