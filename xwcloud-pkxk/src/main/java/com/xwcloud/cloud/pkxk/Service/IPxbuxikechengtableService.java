package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
public interface IPxbuxikechengtableService extends IService<Pxbuxikechengtable> {
    List<Pxbuxikechengtable> selectbuxikecheng(QueryWrapper queryWrapper);

    List<ExportReKeshiVo> ExportRekeshi(QueryWrapper queryWrapper);

    List<Pxbuxikechengtable> getBxByStuAndClass(Long stuID, Long classID,Long qiyeID);

    Page<RemainkeshiDetailsVo> getRemainkeshiDetailsPage(Page page, QueryWrapper queryWrapper);

    Page<RemainDaysVo> getRemainDaysPage(Page page, QueryWrapper queryWrapper);

    Page<UpdatekeshiAndXFVo> getUpdatekeshiAndXFPage(Page page, QueryWrapper queryWrapper);

    List<SumbxRemainVo> getSumRks(Long stuID, Long kechengID, Long qiyeID);

    Page<AutoxiaokeVO> getAutoPage(Page page, QueryWrapper queryWrapper);

    List<AutoxiaokeVO> ExportAutoPage(QueryWrapper queryWrapper);

    List<classstuCountVo> getstuCount(Long paike);

    List<Pxbuxikechengtable> getbuxi(Long stuID, Long paikeID, Long qiyeID);

    Page<classkaoqingVo> getclasskaoqingPage(Page page, Long paikeID, Long classID);

    List<classstuCountVo> getrengongstuCount(Long paike);

    List<Pxbuxikechengtable> getbxbystuclass(Long stuID, Long classID);

    List<SumbxRemainVo> getSumzongRks(Long stuID);

    List<Pxbuxikechengtable> getbuxiBystuAcla(Long stuID, Long classID);

    List<nopaikegetStuVo> getStuNoPaike(String haveclassDate, String startClassDateTime, String endClassDateTime, Long classID,QueryWrapper queryWrapper);

    List<nopaikegetStuVo> getaddstuList(Long stuID, Long buxiID);

    String getbxremainkeshi(Wrapper wrapper);
}
