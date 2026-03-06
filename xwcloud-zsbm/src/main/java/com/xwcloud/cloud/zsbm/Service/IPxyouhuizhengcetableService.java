package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.youhuizhengceVO;
import com.xwcloud.cloud.model.Vo.youhuizhengcexuanzeVO;
import com.xwcloud.cloud.model.entity.Pxyouhuizhengcetable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-11
 */
public interface IPxyouhuizhengcetableService extends IService<Pxyouhuizhengcetable> {
    Pxyouhuizhengcetable getYouhuizhengceById(Long Id);

    Page<youhuizhengceVO> GetYouhuizhengcePages(Page page, QueryWrapper wrapper);

    List<youhuizhengcexuanzeVO> youhuizhengceListBystuGrade(String stuGradeID, QueryWrapper queryWrapper);
}
