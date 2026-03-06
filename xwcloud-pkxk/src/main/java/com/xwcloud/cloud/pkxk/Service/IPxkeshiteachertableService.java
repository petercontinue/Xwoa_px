package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxkeshiteachertable;
import com.xwcloud.cloud.model.Vo.TeaKehaoVo;
import com.xwcloud.cloud.model.Vo.teakehaoCountVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-04
 */
public interface IPxkeshiteachertableService extends IService<Pxkeshiteachertable> {
    List<Pxkeshiteachertable> selectTeakehao(QueryWrapper queryWrapper);
    Page<TeaKehaoVo> getTeakehaoPage(Page page ,QueryWrapper queryWrapper);
    List<TeaKehaoVo> ExportTeakehao(QueryWrapper queryWrapper);
    List<teakehaoCountVo> ExportTeakehaoCount(QueryWrapper queryWrapper);
}
