package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxstutable;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-12
 */
public interface IPxstutableService extends IService<Pxstutable> {
    public Pxstutable GetYixiangStuByID(Long Id);

    Pxstutable updateyxstu(Long id ,Long qiyeID);

    int UpdateStuRemainChongzhi(BigDecimal remainChongzhi, Long stuID);

    List<StuYueInfoVo> getAllChongzhiList(QueryWrapper wrapper);

    List<Pxstutable> getStuByZidingyi(QueryWrapper wrapper);

    List<StuyueVo> getAllStuyueList();

    List<xufeistuVO> GetAllXufeistuList(QueryWrapper queryWrapper);

    List<qdkeshiVo> getqiandankeshi(QueryWrapper queryWrapper);

    List<qdCountVo> getqiandanCountlist(QueryWrapper queryWrapper);


    Page<yixiangStuVo> getYixiangstuPages(Page page, QueryWrapper wrapper);

    Page<genjinTixingVo> GetYixiangStuTixingPages(Page page, QueryWrapper wrapper);

    List<PxStuTableVo> getExportYxStuList(QueryWrapper wrapper);
}
