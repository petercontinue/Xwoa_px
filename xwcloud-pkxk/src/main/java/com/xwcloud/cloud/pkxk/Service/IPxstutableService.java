package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxstutable;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-04
 */
public interface IPxstutableService extends IService<Pxstutable> {
    Page<stuRemainVo> remainkeshishowPage(Page page, QueryWrapper queryWrapper);
    List<ExportReMoneyVo> ExportReMoney(QueryWrapper queryWrapper);
    Page<qiandanstuVo> getqiandanstuShowPage(Page page, QueryWrapper queryWrapper);
    Page<ArtificialQiandaoVo> getPaikeQiandaoPage(Page page, QueryWrapper queryWrapper);
    List<Pxstutable> selectstu(QueryWrapper queryWrapper);
    List<getstuVo> getstu(QueryWrapper queryWrapper);
    List<getclassVo> GetcampusStuName(Long qiyeID);
    List<getclassVo> getxkStu(Long stuID,Long qiyeID);
    Page<HashMap<String, Object>> GetAllStuInfoAndMubanImages(Page page, QueryWrapper queryWrapper);
}
