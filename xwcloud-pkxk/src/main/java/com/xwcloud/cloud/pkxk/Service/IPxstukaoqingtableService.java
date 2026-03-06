package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.KaoqingCountVo;
import com.xwcloud.cloud.model.Vo.KaoqingliushuiVo;
import com.xwcloud.cloud.model.Vo.nokaoqingStuVo;
import com.xwcloud.cloud.model.entity.Pxstukaoqingtable;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
public interface IPxstukaoqingtableService extends IService<Pxstukaoqingtable> {
    List<Pxstukaoqingtable> selectstukaoqing( QueryWrapper queryWrapper);
    Page<KaoqingCountVo> getKaoqingCountPage(Page page, QueryWrapper queryWrapper);
    List<KaoqingCountVo> ExportKaoqingCount(QueryWrapper queryWrapper);
    Page<KaoqingliushuiVo> getKaoqingliushuiPage(Page page, QueryWrapper queryWrapper);
    List<KaoqingliushuiVo> ExportKaoqingliushui(QueryWrapper queryWrapper);
    Page<nokaoqingStuVo> NokaoqingstuPage(Page page, @Param("ew") QueryWrapper queryWrapper);
}
