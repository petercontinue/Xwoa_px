package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.SumkehaoVo;
import com.xwcloud.cloud.model.Vo.stukehaoShowVo;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;
import com.xwcloud.cloud.model.pkxk.Vo.stuKehaoVo;

import com.xwcloud.cloud.model.Vo.xiaokedayingVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-22
 */
public interface IPxkeshistutableService extends IService<Pxkeshistutable> {
    Page<stuKehaoVo> getStukehao(Page page, QueryWrapper queryWrapper);
    SumkehaoVo getkehao(Long buxiID, Long qiyeID);
    List<stuKehaoVo> ExportStukehao(QueryWrapper queryWrapper);
    List<Pxkeshistutable> selectkehao(QueryWrapper queryWrapper);
    Page<stukehaoShowVo> getstukehaoShowPage(Page page, QueryWrapper queryWrapper);
    Page<xiaokedayingVo> getxiaokedayingPage(Page page);
}
